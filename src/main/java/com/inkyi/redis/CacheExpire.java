package com.inkyi.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数注解, 标记参数为key的有效期,单个方法参数中有且仅有一个, 多个默认使用第一个
 * 被标注的值, 0永久 >0有效期
 * @author dingyaming
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER})
public @interface CacheExpire {
	
}