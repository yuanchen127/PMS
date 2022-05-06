package org.pms.javabase.test.thread;

import org.junit.Test;
import org.pms.javabase.thread.MyThread;

public class MythreadTest {

	@Test
	public void test() {
		MyThread thread = new MyThread();
		thread.run();
	}

}
