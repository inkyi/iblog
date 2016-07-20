package com.inkyi.bloomFilter;

/**
 * A {@link BloomFilter} is a probabilistic data structure allowing for space-efficient membership tests on
 * large streams of data elements. Negative results are definitive but false positives might occur.
 */
public interface BloomFilter<E extends Object> {

    /**
     * 添加到过滤器
     * @param element The element must produce a deterministic hash via a {@link com.inkyi.bloomFilter.hashing.HashProvider}.
     */
    void add(E element);

    /**
     * 检查是否已添加到筛选器中的元素。
     * @param element The element must produce a deterministic hash via a {@link com.inkyi.bloomFilter.hashing.HashProvider}.
     * @return False - 不存在 ，True - 可能存在
     */
    boolean mightContain(E element);

}
