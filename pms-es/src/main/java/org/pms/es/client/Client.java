package org.pms.es.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
public class Client {
	private static TransportClient client;

	public static TransportClient getClients(String esConfig) throws NumberFormatException, UnknownHostException {
		Properties props = readProperties(esConfig);
		String clusterName = props.getProperty("clusterName");
		String ipAddresses = props.getProperty("host");
		String[] hosts = ipAddresses.split(",");
		Settings settings = Settings.builder().put("cluster.name", clusterName).build();
		client = TransportClient.builder().settings(settings).build();
		for (String host : hosts) {
			client = ((TransportClient) client).addTransportAddress(new InetSocketTransportAddress(
					InetAddress
							.getByName(host != null && host.split(":").length > 0 ? host.split(":")[0] : "127.0.0.1"),
					Integer.valueOf(host != null && host.split(":").length > 1 ? host.split(":")[1] : "9300")));
		}
		return client;
	}

	private static Properties readProperties(String fileName) {
		Properties props = new Properties();
		File file = new File(fileName);
		if (file.exists()) {
			System.out.println("success to read " + fileName);
			try {
				FileInputStream is = new FileInputStream(fileName);
				props.load(new InputStreamReader(is, "UTF-8"));
			} catch (Exception e) {
				System.out.println("readEsConfig exception");
				e.printStackTrace();
			}
		} else {
			System.out.println("file is not exist:" + fileName);
		}
		return props;
	}

}
