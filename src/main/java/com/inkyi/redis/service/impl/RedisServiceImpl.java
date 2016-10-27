package com.inkyi.redis.service.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.inkyi.redis.service.RedisService;
@Service
public class RedisServiceImpl implements RedisService {

	//private static final Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

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

	@Override
	public void set(String key, String value) {
		set(getStringSerialize(key), getStringSerialize(value));
	}

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
	public Long del(String... keys) {
		return del(getByteParams(keys));
	}

	@Override
	public Long del(byte[]... keys) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.del(keys);
			}
		});
	}

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

	@Override
	public byte[] get(byte[] key) {
		return redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) {
				return connection.get(key);
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
	
	
	/*---------------------------list----------------------------*/
	
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
	public Long lPush(String key, String... value) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.lPush(getStringSerialize(key),
						getByteParams(value));
			}
		});
	}
	
	@Override
	public Long rPush(String key, String value) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.rPush(getStringSerialize(key),
						getStringSerialize(value));
			}
		});
	}

	@Override
	public Long rPush(String key, String... value) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.rPush(getStringSerialize(key),
						getByteParams(value));
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
	public String rPop(String key) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) {
				return getStringDeserialize(connection
						.rPop(getStringSerialize(key)));
			}
		});
	}
	
	@Override
	public Long lLen(String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.lLen(getStringSerialize(key));
			}
		});
	}
	
	@Override
	public byte[] lindex(byte[] key, long index) {
		return redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) {
				return connection.lIndex(key, index);
			}
		});
	}
	
	@Override
	public String lindex(String key, long index) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) {
				return getStringDeserialize(connection.lIndex(getStringSerialize(key), index));
			}
		});
	}
	
	
	/*---------------------------map----------------------------*/
	
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

}
