package org.pms.javabase.collection;

import java.util.LinkedList;
import java.util.List;

public class LinkedListCase {
	private LinkedList link = null;
	private static int index=0;
	public LinkedList getLink() {
		return link;
	}
	public void setLink(LinkedList link) {
		this.link = link;
	}

	public void mainCase() {
		List<String> list = new LinkedList<String>();
		list.add("a");
		list.add("c");
		list.add("b");
		System.out.println(list);
	}
	public <T> void push(T t) {
		link.addFirst(t);
	}
	
	
}
