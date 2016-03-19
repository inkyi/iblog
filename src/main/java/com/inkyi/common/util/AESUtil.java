package com.inkyi.common.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESUtil {
	
	/**
	 * 将byte[]转为各种进制的字符串
	 * @param bytes byte[]
	 * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
	 * @return 转换后的字符串
	 */
	private static String binary(byte[] bytes, int radix){
		return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
	}
	
	/**
	 * base 64 encode
	 * @param bytes 待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	private static String base64Encode(byte[] bytes){
		//正则去除空格
		return new BASE64Encoder().encode(bytes).replaceAll("[\\s*\t\n\r]", "");//去除回车换行
	}
	
	/**
	 * base 64 decode
	 * @param base64Code 待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	private static byte[] base64Decode(String base64Code){
		try {
			return (base64Code==null) ? null : new BASE64Decoder().decodeBuffer(base64Code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 throw new IllegalStateException("System doesn't support AESUtil.base64Decode");
		}
	}
	
	/**
	 * 获取byte[]的md5值
	 * @param bytes byte[]
	 * @return md5
	 * @throws Exception
	 */
	private static byte[] md5(byte[] bytes) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			 throw new IllegalStateException("System doesn't support md5");
		}
		md.update(bytes);
		
		return md.digest();
	}
	
	/**
	 * 获取字符串md5值
	 * @param msg 
	 * @return md5
	 * @throws Exception
	 */
	private static byte[] md5(String msg) {
		return (msg==null) ? null : md5(msg.getBytes());
	}
	
	/**
	 * 结合base64实现md5加密
	 * @param msg 待加密字符串
	 * @return 获取md5后转为base64
	 * @throws Exception
	 */
	private static String md5Encrypt(String msg){
		return (msg==null) ? null : base64Encode(md5(msg));
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
			kgen.init(128, new SecureRandom(encryptKey.getBytes()));

			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
			
			return cipher.doFinal(content.getBytes("utf-8"));
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
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
			kgen.init(128, new SecureRandom(decryptKey.getBytes()));
			
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
			decryptBytes = cipher.doFinal(encryptBytes);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
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
}
