package org.pms.encrypt.md5;

import org.junit.Test;

public class Md5Test {

	@Test
	public void test() {
		IkeMd5 md = new IkeMd5();
		String md5 = md.getMd5("123");
		System.out.println(md5);
	}
}