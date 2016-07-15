package com.inkyi.bloomFilter.hashing;

/**
 * FNV哈希算法
 */
public class FNV1aHash {

	private static final int OFFSET_BASIS = 0x811C9DC5;// 初始的哈希值
	private static final int FNV_PRIME = 0x01000193;// FNV用于散列的质数

	public static int hash(byte[] data) {
		int hash = OFFSET_BASIS;

		for (byte octet : data) {
			hash ^= octet;
			hash *= FNV_PRIME;
		}

		return hash;
	}
}
