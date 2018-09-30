package org.pms.javabase.collection;

import java.util.Stack;

public class StackCase {
	public static void main(String[] args) {
		mainCase();
	}
	
	public static void mainCase() {
		Stack<String> stack = new Stack<String>();
		stack.add("a");
		stack.add("b");
		stack.add("c");
		System.out.println(stack);
	}

}
