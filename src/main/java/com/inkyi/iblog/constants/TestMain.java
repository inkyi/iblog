package com.inkyi.iblog.constants;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

public class TestMain {

/**
 * 二分法查找
 * @author:InkYi
 * @time:2016年6月21日 - 上午11:00:07
 * @param arr 正序排列的有序数组
 * @param value 需要查找的值
 * @return 数组下标
 */
public static int binarySearch(int[] arr, int value) {
	int low = 0;// 查找的最低位
	int high = arr.length - 1;// 查找的最高位
	int mid = 0;// 中间位
	while (low <= high) {
		mid = (low + high) / 2;
		if (arr[mid] == value) {
			return mid;
		} else if (value < arr[mid]) {
			high = mid - 1;
		} else if (value > arr[mid]) {
			low = mid + 1;
		}
	}
	return -1;
}

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int binarySearch = binarySearch(a, 5);
		System.out.println(binarySearch);
		Bag<String> bog = new HashBag<>();
		
		
		
	}
}



