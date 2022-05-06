package org.pms.javabase.collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueCase {
	public static void main(String[] args) {
		mainCase();
	}
	
	public static void mainCase() {
		Queue<String> que = new LinkedList<String>();
		que.add("a");
		que.add("c");
		que.add("b");
		
		System.out.println(que);
	}
}
