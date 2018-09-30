package org.pms.es.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;

public class TestES {
	final String ES_CONFIG_FILE="elasticsearch.properties";
	
	public static void main(String[] args) {
		
	}
	
	private void esTest(){
		try {
			Client client = TransportClient
					.builder()
					.build()
					.addTransportAddress(
							new InetSocketTransportAddress(InetAddress
									.getByName("127.0.0.1"), 9300));
			SearchRequestBuilder builder = client
					.prepareSearch("test-2017-08-23").setTypes("test")
					.setSearchType(SearchType.DEFAULT).setFrom(0).setSize(100);
			BoolQueryBuilder qb = QueryBuilders.boolQuery()
					.must(new QueryStringQueryBuilder("北京").field("body"))
					.should(new QueryStringQueryBuilder("太多").field("body"));
			builder.setQuery(qb);
//			SearchResponse response = builder.execute().actionGet();
			
			SearchResponse response = client.prepareSearch("test-2017-08-23").setTypes("test").execute().actionGet();
			System.out.println("  " + response);
			System.out.println(response.getHits().getTotalHits());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Client getClient() throws UnknownHostException{
		Properties props = readProperties(ES_CONFIG_FILE);
		String clusterName = props.getProperty("clusterName");
		String ipAddresses = props.getProperty("host");
		String[] hosts = ipAddresses.split(",");
		Settings settings = Settings.builder().put("cluster.name",clusterName).build();
		Client client = TransportClient.builder().settings(settings).build();
		for(String host:hosts){
			client = ((TransportClient)client).addTransportAddress(
						new InetSocketTransportAddress(InetAddress.getByName(host!=null && host.split(":").length>0?host.split(":")[0]:"127.0.0.1")
								,Integer.valueOf(host!=null && host.split(":").length>1?host.split(":")[1]:"9300")));
		}
		return client;
	}
	
	private Properties readProperties(String fileName){
		Properties props = new Properties();
		File file = new File(fileName);
		if(file.exists()){
			System.out.println(fileName+" exist!");
			try{
				FileInputStream is = new FileInputStream(fileName);
				props.load(new InputStreamReader(is,"UTF-8"));
			}catch(Exception e){
				System.out.println("readEsConfig exception");
				e.printStackTrace();
			}
		}else{
			System.out.println(fileName +" not exist");
		}
		return props;
	}
	
	private void createIndex(String indexName) throws UnknownHostException{
		IndicesAdminClient client = getClient().admin().indices();
		client.prepareCreate(indexName)
			.get();
	}
	
	private void add() throws UnknownHostException, IOException{
		getClient().prepareIndex("ike","ikeTypes","1")
			.setSource(XContentFactory.jsonBuilder().startObject()
					.field("hostName","ike_1")
					.field("matched","1")
					.endObject())
					.execute()
					.actionGet();
	}
	
	private void indexExist(String index){
		try{
			Client client = TransportClient
					.builder()
					.build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
			IndicesExistsRequest existRequest = new IndicesExistsRequest("ike");
			IndicesExistsResponse existResponse = client.admin().indices().exists(existRequest).actionGet();
			if(!existResponse.isExists()){
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		 
		
	}

}
