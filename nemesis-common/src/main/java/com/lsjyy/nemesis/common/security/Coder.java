package com.lsjyy.nemesis.common.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * 基础加密组件
 * 
 * @author 徐艮权
 * @version 1.0
 * @since 1.0
 */
public abstract class Coder {
	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";

	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 * HmacMD5  
	 * HmacSHA1  
	 * HmacSHA256   
	 * HmacSHA384  
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 */
	public static byte[] decryptBASE64(String key) {
		return Base64.decodeBase64(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 */
	public static String encryptBASE64(byte[] key) {
		return Base64.encodeBase64String(key);
	}

	/**
	 * BASE64加密（URL-Safe）
	 * 
	 * @param key
	 * @return
	 */
	public static String encodeBase64URLSafeString(byte[] key) {
		return Base64.encodeBase64URLSafeString(key);
	}

	/**
	 * 判读两个Base64编码的字符串是否相同（可能用URL-Safe编码）
	 * 
	 * @param base64String1
	 * @param base64String2
	 * @return
	 */
	public static boolean equals(String base64String1, String base64String2) {
		int minLen = Math.min(base64String1.length(), base64String2.length());
		int maxLen = Math.max(base64String1.length(), base64String2.length());

		for (int i = 0; i < minLen; i++) {
			char ch1 = base64String1.charAt(i);
			char ch2 = base64String2.charAt(i);

			if (ch1 == ch2)
				continue;
			if (ch1 == '-' && ch2 == '+' || ch1 == '+' && ch2 == '-')
				continue;
			if (ch1 == '/' && ch2 == '_' || ch1 == '_' && ch2 == '/')
				continue;

			return false;
		}

		for (int i = minLen; i < maxLen; i++) {
			if (i < base64String1.length() && base64String1.charAt(i) != '=')
				return false;
			if (i < base64String2.length() && base64String2.charAt(i) != '=')
				return false;
		}

		return true;
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);

		return md5.digest();
	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);

		return sha.digest();
	}

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal(data);
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws BusiException
	 */
	public static String encryptHMACBase64URLSafeString(byte[] data, String key)
			throws Exception {
		return Base64.encodeBase64URLSafeString(encryptHMAC(data, key));
	}

}
