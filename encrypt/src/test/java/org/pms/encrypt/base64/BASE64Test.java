package org.pms.encrypt.base64;

import org.junit.Test;

import junit.framework.TestCase;

public class BASE64Test {
	
	@Test
	public void test() {
		IkeBase64 base = new IkeBase64();
		String key = "Admin";
		String code = base.encrypt(key);
		System.out.println(code);
		System.out.println(base.decrypt(code));
	}

}
