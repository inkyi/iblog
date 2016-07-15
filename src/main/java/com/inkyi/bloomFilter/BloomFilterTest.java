package com.inkyi.bloomFilter;

public class BloomFilterTest {

	private static BloomFilter<String> filter = null;
	
	
	
	public static void main(String[] args) {
		
		filter = BloomFilterBuilder.get().buildFilter();
		filter = BloomFilterBuilder.get().buildCountingFilter();
		filter = BloomFilterBuilder.get().buildStableFilter();
		
		
	}
}
