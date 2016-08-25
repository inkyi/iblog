package com.inkyi.common.util;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
	
	
	/**
	 * base 64 encode
	 * @param bytes 待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	private static String base64Encode(byte[] bytes){
		//正则去除空格
		return new String(Base64.getEncoder().encodeToString(bytes)).replaceAll("[\\s*\t\n\r]","");//去除回车换行
	}
	
	/**
	 * base 64 decode
	 * @param base64Code 待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	private static byte[] base64Decode(String base64Code){
		try {
			return (base64Code==null) ? null : Base64.getDecoder().decode(base64Code);//   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 throw new IllegalStateException("System doesn't support AESUtil.base64Decode");
		}
	}
	
	
	/**
	 * AES加密
	 * @param content 待加密的内容
	 * @param encryptKey 加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	private static byte[] aesEncryptToBytes(String content, String encryptKey) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			 SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
			 secureRandom.setSeed(encryptKey.getBytes()); 
			kgen.init(128, secureRandom);

			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
			
			return cipher.doFinal(content.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
			 throw new IllegalStateException("System doesn't support AESUtil.aesEncryptToBytes");
		}
	}
	
	/**
	 * AES加密为base 64 code
	 * @param content 待加密的内容
	 * @param encryptKey 加密密钥
	 * @return 加密后的base 64 code
	 * @throws Exception
	 */
	public static String aesEncrypt(String content, String encryptKey) {
		return base64Encode(aesEncryptToBytes(content, encryptKey));
	}
	
	/**
	 * AES解密
	 * @param encryptBytes 待解密的byte[]
	 * @param decryptKey 解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) {
		byte[] decryptBytes;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			 SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
			 secureRandom.setSeed(decryptKey.getBytes());
			kgen.init(128, secureRandom);
			
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
			decryptBytes = cipher.doFinal(encryptBytes);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException("System doesn't support AESUtil.aesDecryptByBytes");
		}
		
		return new String(decryptBytes);
	}
	
	/**
	 * 将base 64 code AES解密
	 * @param encryptStr 待解密的base 64 code
	 * @param decryptKey 解密密钥
	 * @return 解密后的string
	 * @throws Exception
	 */
	public static String aesDecrypt(String encryptStr, String decryptKey) {
		return (encryptStr==null) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
	}
	
	public static void main(String[] args) {
	}
}
