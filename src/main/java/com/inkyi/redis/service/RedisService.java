package com.inkyi.redis.service;

import java.util.Set;

public interface RedisService {
	/**
	 * 将字符串值 value 关联到 key 。
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void set(String key, String value);

	/**
	 * 将字符串值 value 关联到 key 。
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void set(byte[] key, byte[] value);

	/**
	 * 删除
	 * 
	 * @param keys
	 * @return
	 */
	public abstract Long del(String... keys);

	/**
	 * 删除
	 * 
	 * @param keys
	 * @return
	 */
	public abstract Long del(byte[]... keys);

	/**
	 * 获取key
	 */
	public abstract String get(String key);

	/**
	 * 获取key
	 */
	public abstract byte[] get(byte[] key);

	/**
	 * 执行脚本
	 * 
	 * @param script
	 *            脚本
	 * @param resultType
	 *            返回值类型
	 * @param value
	 *            脚本内参数(依照数据顺序KEYS[1],KEYS[2])
	 * @return
	 */
	public abstract <T> T eval(String script, Class<T> resultType,
			String... value);

	/**
	 * 执行脚本
	 * 
	 * @param scriptSha
	 * @param resultType
	 * @param value
	 * @return
	 */
	public abstract <T> T evalSha(String scriptSha, Class<T> resultType,
			String... value);

	/**
	 * list.add
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract Long lPush(String key, String value);

	/**
	 * list.addall
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract Long lPush(String key, String... value);

	/**
	 * list.size
	 * 
	 * @param key
	 * @return
	 */
	public abstract Long lLen(String key);

	/**
	 * 删除目标redis全部DB缓存
	 */
	public abstract void flushAll();

	/**
	 * 预加载脚本
	 * 
	 * @param script
	 * @return sha值
	 */
	public abstract String scriptLoad(String script);

	public abstract Long lPush(String key, String value, Long seconds);

	/**
	 * key 添加有效时间
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public abstract Boolean expire(String key, Long seconds);

	/**
	 * 判断map中是否存在
	 * 
	 * @param key
	 *            redis 中的 key
	 * @param hKey
	 *            Map 中的key
	 * @return
	 */
	public abstract Boolean hExists(String key, String hKey);

	/**
	 * 获取并移除 队首
	 * 
	 * @param key
	 * @return
	 */
	public abstract String lPop(String key);

	/**
	 * 
	 * 将 key 的值设为 value ，当且仅当 key 不存在。 若给定的 key 已经存在，则 SETNX 不做任何动作。 SETNX 是『SET
	 * if Not eXists』(如果不存在，则 SET)的简写。
	 * 
	 * @param key
	 * @param value
	 * @return 设置成功，返回 1 。设置失败，返回 0 。
	 */
	public Boolean setNX(String key, String value);

	/**
	 * 同{@link #setNX(String, String)}
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean setNX(byte[] key, byte[] value);

	/**
	 * 
	 * <blockquote>
	 * 
	 * <pre>
	 * KEYS pattern
	 * 
	 * 查找所有符合给定模式 pattern 的 key 。
	 * 
	 * KEYS * 匹配数据库中所有 key 。
	 * KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。
	 * KEYS h*llo 匹配 hllo 和 heeeeello 等。
	 * KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。
	 * 特殊符号用 \ 隔开
	 * 
	 * KEYS 的速度非常快，但在一个大的数据库中使用它仍然可能造成性能问题，如果你需要从一个数据集中查找特定的 key ，你最好还是用 Redis 的集合结构(set)来代替。
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param patternKey
	 * @return HashSet
	 */
	public Set<String> keys(String patternKey);

	/**
	 * 
	 * <blockquote>
	 * 
	 * <pre>
	 * 发布消息
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param msgKey
	 * @param msg
	 * @return
	 */
	public Long publish(String msgKey, String msg);

	/**
	 * 乐观锁更新redis,继承有效期;
	 * <blockquote>
	 * </blockquote>
	 * @param key
	 * @param newValue
	 * @param oldValue
	 * @return 有且仅有更新成功返回true
	 */
	boolean setByLock(String key, String newValue, String oldValue);

}
