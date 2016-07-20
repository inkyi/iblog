package com.inkyi.bloomFilter;

import com.inkyi.common.util.RandomUtils;
/**
 * 1.饥渴的蜘蛛
 * 2.短网址发放
 * 3.单群聊天室
 * @author InkYi
 *
 */
public class HungerSpider {

	private static BloomFilter<String> filter = null;

	public static String getWebsite(int length) {
		String str = "http://www."+RandomUtils.mixed(length)+".com";
		return str;
	}
	
	public static void main(String[] args) {
		String website = getWebsite(4);
		filter = BloomFilterBuilder.get().buildStableFilter();
		if(!filter.mightContain(website)){
			//进行采集
			filter.add(website);
		}
	}

}
