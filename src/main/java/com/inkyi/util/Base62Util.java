package com.inkyi.util;
/**
 * 62进制转换工具类
 * @author InkYi
 *
 */
public class Base62Util {

	private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String encoding(long num) {
		if (num < 1) {
			throw new IllegalArgumentException("num must be greater than 0.");
		}

		StringBuilder sb = new StringBuilder();
		for (; num > 0; num /= 62) {
			sb.append(ALLCHAR.charAt((int) (num % 62)));
		}

		return sb.toString();
	}

	public static long decoding(String str) {
		if (str == null || str.length() < 1) {
			throw new IllegalArgumentException("str must not be empty.");
		}
		str = str.trim();

		long result = 0;
		for (int i = 0; i < str.length(); i++) {
			result += ALLCHAR.indexOf(str.charAt(i)) * Math.pow(62, i);
		}

		return result;
	}

}
