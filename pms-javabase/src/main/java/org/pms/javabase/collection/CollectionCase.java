package org.pms.javabase.collection;

public class CollectionCase {
	public static int index;
	public static void main(String[] args) {
		System.out.println(get());
		System.out.println(get());
		System.out.println(get());
		System.out.println(get());
		System.out.println(get());
	}
	
	public static String get() {
		String[] strs = new String[] {"a","b","c"};
		if(index>strs.length-1) {
			return null;
		}
		return strs[index++];
	}
	
	

}
