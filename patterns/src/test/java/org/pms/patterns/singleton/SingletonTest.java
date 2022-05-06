package org.pms.patterns.singleton;

import org.junit.Test;

public class SingletonTest {
	@Test
	public void test() {
		Singleton instance = Singleton.getInstance();
		System.out.println("instance:"+instance);
		Singleton innerInstance = Singleton.getInstanceInner();
		System.out.println("innerInstance:"+innerInstance);
	}
}
