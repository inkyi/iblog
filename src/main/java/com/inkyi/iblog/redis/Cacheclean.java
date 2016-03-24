package com.inkyi.iblog.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法注解,清理缓存
 * 
 * <blockquote>
 * 
 * <pre>
 * 示例1 根据@Cacheclean.key 或者@CacheKey 删除缓存内容
 * &#064;Cacheclean(key = &quot;wxid:gh_62d0f03aeebf&quot;)
 * public void deleteByWxid(@CacheKey String wxid) {
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
public @interface Cacheclean {
	/**
	 * 待清理的key
	 * 
	 * @return
	 */
	public String key() default ""; // 缓存key

}