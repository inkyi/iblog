package com.inkyi.redis.service;

import java.util.List;
import java.util.Set;

public interface RedisService {
	
	/*---------------key-value-----------*/
	
	/**
	 * 将字符串值 value 关联到 key 。
	 */
	public abstract void set(String key, String value);
	/**
	 * 将字符串值 value 关联到 key 。
	 */
	public abstract void set(byte[] key, byte[] value);
	/**
	 * 获取key
	 */
	public abstract String get(String key);
	/**
	 * 获取key
	 */
	public abstract byte[] get(byte[] key);
	/**
	 * 删除
	 */
	public abstract Long del(String... keys);
	/**
	 * 删除
	 */
	public abstract Long del(byte[]... keys);
	/**
	 * 将 key 的值设为 value ，当且仅当 key不存在。 
	 * 若给定的 key 已经存在，则 SETNX 不做任何动作。 
	 * SETNX 是『SET if Not eXists』(如果不存在，则 SET)的简写。
	 */
	public Boolean setNX(String key, String value);
	/**
	 * 将 key 的值设为 value ，当且仅当 key 不存在。 
	 * 若给定的 key 已经存在，则 SETNX 不做任何动作。 
	 * SETNX 是『SET if Not eXists』(如果不存在，则 SET)的简写。
	 */
	public Boolean setNX(byte[] key, byte[] value);

	
	/*---------------------------list----------------------------*/
	
	/**
	 * list.add
	 * 在key对应list的头部添加字符串元素
	 */
	public abstract Long lPush(String key, String value);

	/**
	 * list.addall
	 */
	public abstract Long lPush(String key, String... value);

	/**
	 * list.add
	 * 在key对应list的尾部添加字符串元素：
	 */
	public abstract Long rPush(String key, String value);

	/**
	 * list.addall
	 */
	public abstract Long rPush(String key, String... value);
	
	
	/**
	 * 从list的头部删除元素，并返回删除元素：
	 */
	public abstract String lPop(String key);

	/**
	 * 从list的尾部删除元素，并返回删除元素：
	 */
	public abstract String rPop(String key);
	
	/**
	 * list.size
	 */
	public abstract Long lLen(String key);
	
	/**
	 * 返回名称为key的list中index位置的元素：
	 */
	public abstract byte[] lindex(byte[] key, long index);
	
	/**
	 * 返回名称为key的list中index位置的元素：
	 */
	public abstract String lindex(String key, long index);
	
	/**
	 * 根据begin和end获取list 如果begin=0,end=-1获取全部list
	 */
	public abstract List<String> lRange(String key, Long begin, Long end);
	
	/**
	 * 剪切列表
	 */
	public abstract void lTrim(String key, Long begin, Long end);
	/**
	 * 移除等于value的元素，
	 * @description:
	 * 当count>0时，从表头开始查找，移除count个,
	 * 当count=0时，从表头开始查找，移除所有等于value的,
	 * 当count<0时，从表尾开始查找，移除|count|个.
	 */
	public abstract Long lRem(String key, Long count, String value);
	

	/*---------------------------map----------------------------*/
	
	/**
	 * 判断map中是否存在
	 */
	public abstract Boolean hExists(String key, String hKey);
	/**
	 * KEYS pattern
	 * 查找所有符合给定模式 pattern 的 key 。
	 * KEYS * 匹配数据库中所有 key 。
	 * KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。
	 * KEYS h*llo 匹配 hllo 和 heeeeello 等。
	 * KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。
	 * 特殊符号用 \ 隔开
	 * 
	 * KEYS 的速度非常快，但在一个大的数据库中使用它仍然可能造成性能问题，如果你需要从一个数据集中查找特定的 key ，你最好还是用 Redis 的集合结构(set)来代替。
	 */
	public Set<String> keys(String patternKey);
	/**
	 * 删除目标redis全部DB缓存
	 */
	public abstract void flushAll();
	/**
	 * key 添加有效时间
	 */
	public abstract Boolean expire(String key, Long seconds);
	/**
	 * key 添加有效时间
	 */
	public abstract Boolean expire(byte[] key, Long seconds);
	/**
	 * 查看KEY是否存在 
	 */
	public abstract boolean exists(String key);
	/**
	 * 查看KEY是否存在 
	 */
	public abstract boolean exists(byte[] key);
	/**
	 * 自增value
	 */
	public abstract Long incr(String key);
	


}
