package com.capitalone.hazelcast.test;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.instance.HazelcastInstanceFactory;

public class AWSHazelcastTest {
	static HazelcastInstance hazelcastClient =null;
	public static void main(String[] a) throws InterruptedException {
		
		/*Connecting to Hazelcast server 1*/
		ClientConfig clientConfig = new ClientConfig();
		GroupConfig gc = clientConfig.getGroupConfig();
		gc.setName("oupf-dev-app-user");
		gc.setPassword("oupf-password");
		clientConfig.getNetworkConfig().addAddress("10.205.201.31:11415");
		 hazelcastClient = HazelcastClient
				.newHazelcastClient(clientConfig);
		IMap<String, String> cache1 = hazelcastClient.getMap("Cache1");
		IMap<String,String> cache2 = hazelcastClient.getMap("Cache2");
		cache1.addEntryListener(new ClientListener(), false);
		cache1.put("a key", "a value");
		System.out.println("AWSHazelcastTest Thread info : "+ Thread.currentThread().getName());
		// map.clear();

		
	}
	
	public static void operationOnOtherCache(){
		System.out.println("Inside other method");
		/*
		ClientConfig clientConfig = new ClientConfig();
		GroupConfig gc = clientConfig.getGroupConfig();
		gc.setName("oupf-dev-app-user");
		gc.setPassword("oupf-password");
		clientConfig.getNetworkConfig().addAddress("10.205.201.31:11415");
		HazelcastInstance hazelcastClient = HazelcastClient
				.newHazelcastClient(clientConfig);*/
		
		IMap<String,String> cache2 = hazelcastClient.getMap("Cache2");
		cache2.put("another key", "another value");
		System.out.println("getting the value from cache 2 : " + cache2.get("another key"));
		int a =5+3;
		System.out.println("Doing some other stuff.....");
		
		System.out.println("The addition of two numbers : "+ a);
	}

	public static HazelcastInstance getHazelcastClient() {
		return hazelcastClient;
	}

	public static void setHazelcastClient(HazelcastInstance hazelcastClient) {
		AWSHazelcastTest.hazelcastClient = hazelcastClient;
	}

}
