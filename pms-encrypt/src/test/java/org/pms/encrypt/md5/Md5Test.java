package org.pms.encrypt.md5;

import org.junit.Test;

public class Md5Test {

	@Test
	public void test() {
		String md5 = IkeMd5.getMd5("123");
		System.out.println(md5);
	}
}