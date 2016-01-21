package com.capitalone.hazelcast.test;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;

public class ClientListener implements EntryListener<String, String> {

	@Override
	public void entryAdded(EntryEvent<String, String> event) {
		System.out.println("Entry Added:" + event);
		System.out.println("Add : Calling the other method");
		System.out.println("ClientListener Thread info : "+ Thread.currentThread().getName());
		AWSHazelcastTest.operationOnOtherCache();
		System.out.println("Add : Other method call returned");
	}

	@Override
	public void entryRemoved(EntryEvent<String, String> event) {
		System.out.println("Entry Removed:" + event);
	}

	@Override
	public void entryUpdated(EntryEvent<String, String> event) {
		System.out.println("Entry Updated:" + event);
		System.out.println("Update : Calling the other method");
		System.out.println("ClientListener Thread info : "+ Thread.currentThread().getName());
		AWSHazelcastTest.operationOnOtherCache();
		System.out.println("Update : Other method call returned");
	}

	@Override
	public void entryEvicted(EntryEvent<String, String> event) {
		System.out.println("Entry Evicted:" + event);

	}

}
