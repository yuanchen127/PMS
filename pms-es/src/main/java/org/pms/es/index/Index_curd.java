package org.pms.es.index;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Index_curd {
	private static Client client;
	final String ES_CONFIG_FILE = "elasticsearch.properties";

	static{
		try {
			client = TransportClient.builder().build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close() throws Exception {
		client.close();
	}

	public void test() throws IOException, InterruptedException {
		SimpleDateFormat sdfSecond = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		SimpleDateFormat sdfDay = new SimpleDateFormat("YYYY-MM-dd");
		// System.out.println(sdf.format(new Date()));
		ObjectMapper mapper = new ObjectMapper();

		// map to json
		Map<String, Object> map = new HashMap();
		map.put("name", "ike");
		map.put("date", sdfSecond.format(new Date()));
		String mapString = mapper.writeValueAsString(map);
		System.out.println(mapString);

		// beanInstance to json
		Index index = new Index("ike", new Date());
		String indexString = mapper.writeValueAsString(index);
		System.out.println(indexString);

		// XContentBuilder build json
		XContentBuilder builder = XContentFactory.jsonBuilder().startObject().field("name", "ike")
				.field("date", sdfSecond.format(new Date())).endObject();
		String builderString = builder.string();
		System.out.println(builderString);

		builder = XContentFactory.jsonBuilder().startArray().value("aaa").value("bbb").endArray();
		System.out.println(builder.string());

		// add(200000);

		// int i=0;
		// SearchResponse response =null;
		// String startAdd = sdfSecond.format(new Date());
		// while(i<20000000/20000){
		// SearchRequestBuilder ikeBuilder = client.prepareSearch("ike");
		// long total = ikeBuilder.execute().actionGet().getHits().getTotalHits();
		// System.out.println(">>"+(20000*i));
		//// Thread.sleep(3000);
		// add((int)(20000*i),20000);
		// i++;
		// }
		// String endAdd = sdfSecond.format(new Date());
		//
		// String startDelete =sdfSecond.format(new Date());
		// delete();
		// String endDelete =sdfSecond.format(new Date());
		//
		// System.out.println("add through:"+startAdd +" to "+endAdd);
		// System.out.println("delete through:"+startDelete +" to "+endDelete);

		SearchResponse sr = client.prepareSearch().setTypes(sdfDay.format(new Date()))
				.setQuery(QueryBuilders.fuzzyQuery("name", "21535")).get();
		System.out.println(sr.getHits().totalHits());
	}

	private void add(int start, int num) throws IOException {
		SimpleDateFormat sdfDay = new SimpleDateFormat("YYYY-MM-dd");
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			System.out.println("add>>" + start);
			bulkRequest.add(client.prepareIndex("ike", sdfDay.format(new Date()))
					.setSource(XContentFactory.jsonBuilder().startObject().field("name", "ike_" + start)
							.field("age", String.valueOf(random.nextInt(11) + 15)).field("createTime", new Date())
							.endObject()));
			start++;
		}
		BulkResponse bulkResponse = bulkRequest.get();
		if (bulkResponse.hasFailures()) {
			System.out.println("hasFailures!!!");
		}
	}

	private void delete() throws InterruptedException {
		SearchRequestBuilder builder = client.prepareSearch("ike");
		long total = builder.execute().actionGet().getHits().getTotalHits();
		System.out.println(total);
		int i = 0, per = 10000, times = 0;
		System.out.println("-------------");
		SearchResponse response = builder.setSize(per).setScroll(new TimeValue(2000)).execute().actionGet();
		while (true) {
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			for (SearchHit hit : response.getHits().getHits()) {
				System.out.println(i + ">>" + hit.id());
				bulkRequest.add(client.prepareDelete("ike", hit.getType(), hit.getId()));
				i++;
				// Thread.sleep(10);
			}
			BulkResponse bulkResponse = bulkRequest.get();
			if (bulkResponse.hasFailures()) {
				System.out.println("hasFailures");
				// Thread.sleep(3000);
			}
			response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute()
					.actionGet();
			// Break condition: No hits are returned
			if (response.getHits().getHits().length == 0) {
				break;
			}
			// times++;
		}
	}

	private void getFields(String index, String type) {
		SearchResponse reponse = client.prepareSearch(index).setSize(1).setTypes(type).execute().actionGet();
		for (SearchHit hit : reponse.getHits().hits()) {
			for (Object s : hit.getSource().keySet().toArray()) {
				System.out.println(s.toString());
			}
		}
	}
}
