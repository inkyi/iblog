package com.inkyi.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法注解,绑定缓存
 * 
 * <blockquote>
 * 
 * <pre>
 * 示例1:使用@CacheKey参数Json序列化成key, return返回值Json序列化成value 
 * &#064;Cacheable(expire = 100)
 * public SysWxuser selectByWxid(@CacheKey String wxid) {
 * };
 * 
 * 示例2 使用@Cacheable.key 自定义 String类型 key, return返回值Json序列化成value, 优先级大于 @CacheKey
 * &#064;Cacheable(expire = 100, key = &quot;wxid:gh_62d0f03aeebf&quot;)
 * public SysWxuser selectByWxid(@CacheKey String wxid) {
 * };
 * </pre>
 * 
 * </blockquote>
 * 
 * @author dingyaming
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Cacheable {

	/**
	 * 待绑定的key
	 * 
	 * @return
	 */
	public String key() default ""; // 缓存key

	/**
	 * <pre>
	 * 有效期,过期后自动删除, <=0 则为永久保存
	 * </pre>
	 * 
	 * @return
	 */
	public long expire() default 0; // 缓存多少秒,默认无限期
}