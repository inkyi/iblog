package com.inkyi.common.util;

import java.util.Random;

public class RandomUtils {

	private static final Random RANDOM = new Random();

	/**
	 * 混合
	 * @author:InkYi
	 * @time:2016年7月19日 - 下午9:01:39
	 * @description:数字 字母混合
	 * @param length
	 * @return
	 */
	public static String mixed(final int length) {
		String str = "";
		for (int i = 0; i < length; i++) {
			boolean b = RANDOM.nextBoolean();
			if (b) {// 字符串
				int choice = RANDOM.nextBoolean() ? 65 : 97; // 取得65大写字母还是97小写字母
				str += (char) (choice + RANDOM.nextInt(26));
			} else { // 数字
				str += String.valueOf(RANDOM.nextInt(10));
			}
		}
		return str;
	}

	/**
	 * 纯字母
	 * @author:InkYi
	 * @time:2016年7月19日 - 下午9:04:04
	 * @description:
	 * @param length
	 * @return 大小写混合
	 */
	public static String letters(final int length) {
		String str = "";
		for (int i = 0; i < length; i++) {
			// 取得65大写字母还是97小写字母
			int choice = RANDOM.nextBoolean() ? 65 : 97;
			str += (char) (choice + RANDOM.nextInt(26));
		}
		return str;
	}

	/**
	 * 纯数字
	 * @author:InkYi
	 * @time:2016年7月19日 - 下午9:02:37
	 * @description:
	 * @param length
	 * @return
	 */
	public static String digit(final int length) {
		String str = "";
		for (int i = 0; i < length; i++) {
			str += String.valueOf(RANDOM.nextInt(10));
		}
		return str;
	}

	public static byte[] nextBytes(final int count) {
		isTrue(count >= 0, "不能是负数");

		final byte[] result = new byte[count];
		RANDOM.nextBytes(result);

		return result;
	}

	public static int nextInt(final int startInclusive, final int endExclusive) {
		isTrue(endExclusive >= startInclusive, "起始值 必须小于或等于 结束值 ");
		isTrue(startInclusive >= 0, "这两个范围值必须是非负的");

		if (startInclusive == endExclusive) {
			return startInclusive;
		}

		return startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
	}

	public static long nextLong(final long startInclusive, final long endExclusive) {
		isTrue(endExclusive >= startInclusive, "起始值 必须小于或等于 结束值 ");
		isTrue(startInclusive >= 0, "这两个范围值必须是非负的");

		if (startInclusive == endExclusive) {
			return startInclusive;
		}

		return (long) nextDouble(startInclusive, endExclusive);
	}

	public static double nextDouble(final double startInclusive, final double endInclusive) {
		isTrue(endInclusive >= startInclusive, "起始值 必须小于或等于 结束值 ");
		isTrue(startInclusive >= 0, "这两个范围值必须是非负的");

		if (startInclusive == endInclusive) {
			return startInclusive;
		}

		return startInclusive + ((endInclusive - startInclusive) * RANDOM.nextDouble());
	}

	public static float nextFloat(final float startInclusive, final float endInclusive) {
		isTrue(endInclusive >= startInclusive, "起始值 必须小于或等于 结束值 ");
		isTrue(startInclusive >= 0, "这两个范围值必须是非负的");

		if (startInclusive == endInclusive) {
			return startInclusive;
		}

		return startInclusive + ((endInclusive - startInclusive) * RANDOM.nextFloat());
	}

	public static void isTrue(final boolean expression, final String message) {
		if (expression == false) {
			throw new IllegalArgumentException(message);
		}
	}

}
