package org.pms.encrypt.base64;

import java.util.Base64;

/**
 * base64加密算法
 * @author ike
 *
 */

public class IkeBase64 {
	/**
	 * 使用base64加密字符串
	 * @param src
	 * @return
	 */
	public String encrypt(String src) {
		byte[] srcByte = src.getBytes();
		String code = Base64.getEncoder().encodeToString(srcByte);
		return code;
	}
	
	/**
	 * 解密base64密码
	 * @param code
	 * @return
	 */
	public String decrypt(String code) {
		byte[] codeByte = code.getBytes();
		byte[] src = Base64.getDecoder().decode(codeByte);
		return new String(src);
	}

}
