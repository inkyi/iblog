package com.inkyi.redis.service.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import com.inkyi.redis.service.RedisService;

public class RedisServiceImpl implements RedisService {

	private static final Logger logger = LoggerFactory
			.getLogger(RedisServiceImpl.class);

	@Resource
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	private byte[] getStringSerialize(String value) {
		return redisTemplate.getStringSerializer().serialize(value);
	}

	private String getStringDeserialize(byte[] value) {
		return redisTemplate.getStringSerializer().deserialize(value);
	}

	private byte[][] getByteParams(String... params) {
		byte[][] p = new byte[params.length][];
		for (int i = 0; i < params.length; i++)
			p[i] = getStringSerialize(params[i]);
		return p;
	}

	/**
	 * 将字符串值 value 关联到 key 。
	 * 
	 * @param key
	 * @param value
	 */
	@Override
	public void set(String key, String value) {
		set(getStringSerialize(key), getStringSerialize(value));
	}

	/**
	 * 将字符串值 value 关联到 key 。
	 * 
	 * @param key
	 * @param value
	 */
	@Override
	public void set(byte[] key, byte[] value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) {
				connection.set(key, value);
				return null;
			}
		});
	}

	@Override
	public Boolean setNX(String key, String value) {
		return setNX(getStringSerialize(key), getStringSerialize(value));
	}

	@Override
	public Boolean setNX(byte[] key, byte[] value) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) {
				return connection.setNX(key, value);
			}
		});
	}

	/**
	 * 删除
	 * 
	 * @param keys
	 * @return
	 */
	@Override
	public Long del(String... keys) {
		return del(getByteParams(keys));
	}

	/**
	 * 删除
	 * 
	 * @param keys
	 * @return
	 */
	@Override
	public Long del(byte[]... keys) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.del(keys);
			}
		});
	}

	/**
	 * 获取key
	 */
	@Override
	public String get(String key) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) {
				return getStringDeserialize(connection
						.get(getStringSerialize(key)));
			}
		});
	}

	/**
	 * 获取key
	 */
	@Override
	public byte[] get(byte[] key) {
		return redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) {
				return connection.get(key);
			}
		});
	}

	/**
	 * 执行脚本
	 * 
	 * @param script
	 * @param resultType
	 * @param value
	 * @return
	 */
	@Override
	public <T> T eval(String script, Class<T> resultType, String... value) {
		String scriptSha = scriptLoad(script);
		return evalSha(scriptSha, resultType, value);
	}

	/**
	 * 执行脚本
	 * 
	 * @param scriptSha
	 * @param resultType
	 * @param value
	 * @return
	 */
	@Override
	public <T> T evalSha(String scriptSha, Class<T> resultType, String... value) {
		return redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection) {
				ReturnType returnType = null;
				if (resultType == null) {
					returnType = ReturnType.STATUS;
				} else if (resultType.isAssignableFrom(String.class)) {
					returnType = ReturnType.STATUS;
				} else if (resultType.isAssignableFrom(List.class)) {
					returnType = ReturnType.MULTI;
				} else if (resultType.isAssignableFrom(Boolean.class)) {
					returnType = ReturnType.BOOLEAN;
				} else if (resultType.isAssignableFrom(Long.class)) {
					returnType = ReturnType.INTEGER;
				}
				return connection.evalSha(scriptSha, returnType, value.length,
						getByteParams(value));
			}
		});
	}

	/**
	 * list.add
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public Long lPush(String key, String value) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.lPush(getStringSerialize(key),
						getStringSerialize(value));
			}
		});
	}

	@Override
	public Long lPush(String key, String value, Long seconds) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				byte[] keyB = getStringSerialize(key);
				Long res = connection.lPush(keyB, getStringSerialize(value));
				if (seconds != null && seconds > 0)
					connection.expire(keyB, seconds);
				return res;
			}
		});
	}

	/**
	 * list.addall
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public Boolean expire(String key, Long seconds) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) {
				byte[] keyB = getStringSerialize(key);
				return connection.expire(keyB, seconds);
			}
		});
	}

	/**
	 * list.addall
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public Long lPush(String key, String... value) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.lPush(getStringSerialize(key),
						getByteParams(value));
			}
		});
	}

	/**
	 * list.size
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Long lLen(String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.lLen(getStringSerialize(key));
			}
		});
	}

	/**
	 * list.size
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Boolean hExists(String key, String value) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) {
				return connection.hExists(getStringSerialize(key),
						getStringSerialize(value));
			}
		});
	}

	/**
	 * 删除目标redis全部DB缓存
	 */
	@Override
	public void flushAll() {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.flushAll();
				return null;
			}
		});
	}

	/**
	 * 预加载脚本
	 * 
	 * @param script
	 * @return sha值
	 */
	@Override
	public String scriptLoad(String script) {
		return redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				return connection.scriptLoad(getStringSerialize(script));
			}
		});
	}

	@Override
	public String lPop(String key) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) {
				return getStringDeserialize(connection
						.lPop(getStringSerialize(key)));
			}
		});
	}

	@Override
	public Set<String> keys(String patternKey) {
		return redisTemplate.execute(new RedisCallback<Set<String>>() {
			@Override
			public Set<String> doInRedis(RedisConnection connection) {
				Set<byte[]> setC = connection
						.keys(getStringSerialize(patternKey));
				Set<String> setT = new HashSet<String>();
				if (setC != null) {
					for (byte[] bs : setC) {
						setT.add(getStringDeserialize(bs));
					}
				}
				return setT;
			}
		});
	}


	@Override
	public Long publish(String msgKey, String msg) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.publish(getStringSerialize(msgKey),
						getStringSerialize(msg));
			}
		});
	}
	
	private static final String SetByLockScript = ""
			+ "local oldValue = redis.call('GET', KEYS[1]);\n"
			+ "if oldValue and type(oldValue)=='string' and oldValue==KEYS[3] then\n"
			+ "local ttls = redis.call('TTL', KEYS[1]); \n"
			+ "redis.call('SET',KEYS[1], KEYS[2]);\n"
			+ "if ttls and ttls>0 then\n"
			+ "redis.call('EXPIRE',KEYS[1],ttls);\n"
			+ "end\n"
			+ "return tostring(true);\n"
			+ "end\n"
			+ "return tostring(false)";
	private static final DefaultRedisScript<String> setByLockScript = new DefaultRedisScript<String>(SetByLockScript, String.class);
	
	@Override
	public boolean setByLock(String key, String newValue, String oldValue) {
		try {
			String res = eval(setByLockScript.getScriptAsString(), String.class , key,newValue,oldValue);
			if(Boolean.TRUE.toString().equals(res)){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	   
}
