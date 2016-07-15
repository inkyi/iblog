package com.inkyi.bloomFilter.hashing;

public interface HashProvider<E> {

	int hash1(E element);

	int hash2(E element);

}
