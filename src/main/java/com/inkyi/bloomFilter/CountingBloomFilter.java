package com.inkyi.bloomFilter;

/**
 * 布隆过滤器的变种
 * @author InkYi
 * 可删除的布隆过滤器
 * @param <E>
 */
public interface CountingBloomFilter<E> extends BloomFilter<E> {

	/**
	 * Remove an element from the filter.
	 * 
	 * @param element
	 *            Must have been added to the filter before. If not, the method
	 *            wont fail but unpredictable side-effects might occur.
	 */
	void remove(E element);

}
