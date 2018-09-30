package org.pms.encrypt.md5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IkeMd5 {
	
	private static final String KEY_MD5 = "MD5";
	
	private static String getMd5(String src) {
		BigInteger big = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance(KEY_MD5);
			byte[] srcByte = src.getBytes();
			md.update(srcByte);
			big = new BigInteger(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return big.toString(16);
	}

}
