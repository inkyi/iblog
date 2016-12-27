package com.inkyi.redis;

public abstract class RedisCash<T> {
	private String rediskey;//key
	private Class<T> clazz;//类
	private Long seconds ;//有效时间

	public RedisCash(String rediskey, Class<T> clazz,Long seconds) {
		super();
		this.rediskey = rediskey;
		this.clazz = clazz;
		this.seconds = seconds;
	}

	public abstract T useCash();

	public String getRediskey() {
		return rediskey;
	}

	public void setRediskey(String rediskey) {
		this.rediskey = rediskey;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public Long getSeconds() {
		return seconds;
	}

	public void setSeconds(Long seconds) {
		this.seconds = seconds;
	}
}