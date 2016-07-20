package com.inkyi.bloomFilter;

/**
 * 布隆过滤器的变种
 * @author InkYi
 * 可删除的布隆过滤器
 * @param <E>
 */
public interface CountingBloomFilter<E> extends BloomFilter<E> {

	/**
	 * 从过滤器中删除一个元素。
	 * @param element
	 * 必须已被添加到过滤器之前。如果没有，该方法不会失败，但不可预知的副作用可能会发生。
	 */
	void remove(E element);

}
