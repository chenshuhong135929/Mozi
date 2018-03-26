package com.sy.redis.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.sy.redis.RedisClientTemplate;
import com.sy.redis.RedisDataSource;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.BinaryClient.LIST_POSITION;

@Repository("redisClientTemplate")
public class RedisClientTemplateImpl implements RedisClientTemplate {

	private static final Logger log = Logger.getLogger(RedisClientTemplateImpl.class);

	@Autowired
	private RedisDataSource redisDataSource;

	/**
	 * <鑾峰彇jedis鏁版嵁婧�>
	 */
	public void disconnect() {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		shardedJedis.disconnect();
	}

	/**
	 * <鍚憆edis鏁版嵁搴撲腑璁剧疆鍊� key value>
	 * 
	 * @param String
	 *            key String value
	 * @return OK
	 */
	public String set(String key, String value) {
		String result = null;

		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.set(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * <鑾峰彇redis涓� KEY瀵瑰簲鐨刅ALUE>
	 * 
	 * @param key
	 * @return value
	 */
	public String get(String key) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		boolean broken = false;
		try {
			result = shardedJedis.get(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * 璁剧疆瀵硅薄
	 */
	@Override
	public <T> String setObject(String key, T t) {
		String str = JSON.toJSONString(t);
		System.out.println("setObjectStr:"+str);
		String res = set(key, JSON.toJSONString(t));
		return res;
	}

	/**
	 * 鑾峰彇瀵硅薄
	 */
	@Override
	public <T> T getObject(String key,Class clazz) {
		String str = get(key);
		if(null == str){
			return null;
		}else{
			System.out.println("getObjectStr:"+str);
		}
		T t2 = (T) JSON.parseObject(str, clazz);
		return t2;
	}
	
	/**
	 * 淇濆瓨 瀵硅薄 骞惰缃湁鏁堟湡
	 */
	@Override
	public <T> String setExpireObject(String key, int seconds, T t) {
		String result = null;

		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		
		String str = JSON.toJSONString(t);
		System.out.println("setExpireObjectStr:"+str);
		
		boolean broken = false;
		try {
			result = shardedJedis.setex(key, seconds, JSON.toJSONString(t));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * <鍒ゆ柇KEY鏄惁瀛樺湪>
	 * 
	 * @param key
	 * @return 瀛樺湪 TRUE 涓嶅瓨鍦� FALSE
	 */
	public Boolean exists(String key) {
		Boolean result = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.exists(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * <杩斿洖 key 鎵�鍌ㄥ瓨鐨勫�肩殑绫诲瀷>
	 * 
	 * @param String
	 *            key
	 * @return none (key涓嶅瓨鍦�) string (瀛楃涓�) list (鍒楄〃)set (闆嗗悎) zset (鏈夊簭闆�) hash
	 *         (鍝堝笇琛�)
	 */
	public String type(String key) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.type(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#expire(java.lang.String,
	 * int)
	 */
	public Long expire(String key, int seconds) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.expire(key, seconds);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#expireAt(java.lang.String,
	 * long)
	 */
	public Long expireAt(String key, long unixTime) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.expireAt(key, unixTime);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#ttl(java.lang.String)
	 */
	public Long ttl(String key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.ttl(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#setbit(java.lang.String,
	 * long, boolean)
	 */
	public boolean setbit(String key, long offset, boolean value) {

		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		boolean result = false;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.setbit(key, offset, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#getbit(java.lang.String,
	 * long)
	 */
	public boolean getbit(String key, long offset) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		boolean result = false;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;

		try {
			result = shardedJedis.getbit(key, offset);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#setrange(java.lang.String,
	 * long, java.lang.String)
	 */
	public long setrange(String key, long offset, String value) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		long result = 0;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.setrange(key, offset, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#getrange(java.lang.String,
	 * long, long)
	 */
	public String getrange(String key, long startOffset, long endOffset) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.getrange(key, startOffset, endOffset);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#getSet(java.lang.String,
	 * java.lang.String)
	 */
	public String getSet(String key, String value) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.getSet(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#setnx(java.lang.String,
	 * java.lang.String)
	 */
	public Long setnx(String key, String value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.setnx(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#setex(java.lang.String, int,
	 * java.lang.String)
	 */
	public String setex(String key, int seconds, String value) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.setex(key, seconds, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#decrBy(java.lang.String,
	 * long)
	 */
	public Long decrBy(String key, long integer) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.decrBy(key, integer);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#decr(java.lang.String)
	 */
	public Long decr(String key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.decr(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#incrBy(java.lang.String,
	 * long)
	 */
	public Long incrBy(String key, long integer) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.incrBy(key, integer);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#incr(java.lang.String)
	 */
	public Long incr(String key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.incr(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#append(java.lang.String,
	 * java.lang.String)
	 */
	public Long append(String key, String value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.append(key, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#substr(java.lang.String,
	 * int, int)
	 */
	public String substr(String key, int start, int end) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.substr(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hset(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public Long hset(String key, String field, String value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.hset(key, field, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hget(java.lang.String,
	 * java.lang.String)
	 */
	public String hget(String key, String field) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.hget(key, field);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hsetnx(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public Long hsetnx(String key, String field, String value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.hsetnx(key, field, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hmset(java.lang.String,
	 * java.util.Map)
	 */
	public String hmset(String key, Map<String, String> hash) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.hmset(key, hash);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hmget(java.lang.String,
	 * java.lang.String)
	 */
	public List<String> hmget(String key, String... fields) {
		List<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.hmget(key, fields);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hincrBy(java.lang.String,
	 * java.lang.String, long)
	 */
	public Long hincrBy(String key, String field, long value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.hincrBy(key, field, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hexists(java.lang.String,
	 * java.lang.String)
	 */
	public Boolean hexists(String key, String field) {
		Boolean result = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.hexists(key, field);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#del(java.lang.String)
	 */
	public Long del(String key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.del(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hdel(java.lang.String,
	 * java.lang.String)
	 */
	public Long hdel(String key, String field) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.hdel(key, field);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hlen(java.lang.String)
	 */
	public Long hlen(String key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.hlen(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hkeys(java.lang.String)
	 */
	public Set<String> hkeys(String key) {
		Set<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.hkeys(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hvals(java.lang.String)
	 */
	public List<String> hvals(String key) {
		List<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.hvals(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hgetAll(java.lang.String)
	 */
	public Map<String, String> hgetAll(String key) {
		Map<String, String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.hgetAll(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	// ================list ====== l琛ㄧず list鎴� left, r琛ㄧずright====================
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#rpush(java.lang.String,
	 * java.lang.String)
	 */
	public Long rpush(String key, String string) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.rpush(key, string);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#lpush(java.lang.String,
	 * java.lang.String)
	 */
	public Long lpush(String key, String string) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.lpush(key, string);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#llen(java.lang.String)
	 */
	public Long llen(String key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.llen(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#lrange(java.lang.String,
	 * long, long)
	 */
	public List<String> lrange(String key, long start, long end) {
		List<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.lrange(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#ltrim(java.lang.String,
	 * long, long)
	 */
	public String ltrim(String key, long start, long end) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.ltrim(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#lindex(java.lang.String,
	 * long)
	 */
	public String lindex(String key, long index) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.lindex(key, index);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#lset(java.lang.String, long,
	 * java.lang.String)
	 */
	public String lset(String key, long index, String value) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.lset(key, index, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#lrem(java.lang.String, long,
	 * java.lang.String)
	 */
	public Long lrem(String key, long count, String value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.lrem(key, count, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#lpop(java.lang.String)
	 */
	public String lpop(String key) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.lpop(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#rpop(java.lang.String)
	 */
	public String rpop(String key) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.rpop(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	// return 1 add a not exist value ,
	// return 0 add a exist value
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#sadd(java.lang.String,
	 * java.lang.String)
	 */
	public Long sadd(String key, String member) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.sadd(key, member);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#smembers(java.lang.String)
	 */
	public Set<String> smembers(String key) {
		Set<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.smembers(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#srem(java.lang.String,
	 * java.lang.String)
	 */
	public Long srem(String key, String member) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();

		Long result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.srem(key, member);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#spop(java.lang.String)
	 */
	public String spop(String key) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.spop(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#scard(java.lang.String)
	 */
	public Long scard(String key) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		Long result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.scard(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#sismember(java.lang.String,
	 * java.lang.String)
	 */
	public Boolean sismember(String key, String member) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		Boolean result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.sismember(key, member);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#srandmember(java.lang.String)
	 */
	public String srandmember(String key) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.srandmember(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zadd(java.lang.String,
	 * double, java.lang.String)
	 */
	public Long zadd(String key, double score, String member) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.zadd(key, score, member);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrange(java.lang.String,
	 * int, int)
	 */
	public Set<String> zrange(String key, int start, int end) {
		Set<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.zrange(key, start, end);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrem(java.lang.String,
	 * java.lang.String)
	 */
	public Long zrem(String key, String member) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.zrem(key, member);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zincrby(java.lang.String,
	 * double, java.lang.String)
	 */
	public Double zincrby(String key, double score, String member) {
		Double result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zincrby(key, score, member);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrank(java.lang.String,
	 * java.lang.String)
	 */
	public Long zrank(String key, String member) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrank(key, member);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrevrank(java.lang.String,
	 * java.lang.String)
	 */
	public Long zrevrank(String key, String member) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrank(key, member);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrevrange(java.lang.String,
	 * int, int)
	 */
	public Set<String> zrevrange(String key, int start, int end) {
		Set<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrange(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrangeWithScores(java.lang.
	 * String, int, int)
	 */
	public Set<Tuple> zrangeWithScores(String key, int start, int end) {
		Set<Tuple> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrangeWithScores(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#zrevrangeWithScores(java.lang.
	 * String, int, int)
	 */
	public Set<Tuple> zrevrangeWithScores(String key, int start, int end) {
		Set<Tuple> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrangeWithScores(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zcard(java.lang.String)
	 */
	public Long zcard(String key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zcard(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zscore(java.lang.String,
	 * java.lang.String)
	 */
	public Double zscore(String key, String member) {
		Double result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zscore(key, member);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#sort(java.lang.String)
	 */
	public List<String> sort(String key) {
		List<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.sort(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#sort(java.lang.String,
	 * redis.clients.jedis.SortingParams)
	 */
	public List<String> sort(String key, SortingParams sortingParameters) {
		List<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.sort(key, sortingParameters);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zcount(java.lang.String,
	 * double, double)
	 */
	public Long zcount(String key, double min, double max) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zcount(key, min, max);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#zrangeByScore(java.lang.String,
	 * double, double)
	 */
	public Set<String> zrangeByScore(String key, double min, double max) {
		Set<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrangeByScore(key, min, max);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrevrangeByScore(java.lang.
	 * String, double, double)
	 */
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		Set<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrangeByScore(key, max, min);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#zrangeByScore(java.lang.String,
	 * double, double, int, int)
	 */
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		Set<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrangeByScore(key, min, max, offset, count);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrevrangeByScore(java.lang.
	 * String, double, double, int, int)
	 */
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		Set<String> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrangeByScore(key, max, min, offset, count);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#zrangeByScoreWithScores(java.lang
	 * .String, double, double)
	 */
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		Set<Tuple> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrangeByScoreWithScores(key, min, max);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#zrevrangeByScoreWithScores(java.
	 * lang.String, double, double)
	 */
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		Set<Tuple> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrangeByScoreWithScores(key, max, min);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#zrangeByScoreWithScores(java.lang
	 * .String, double, double, int, int)
	 */
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		Set<Tuple> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrangeByScoreWithScores(key, min, max, offset, count);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#zrevrangeByScoreWithScores(java.
	 * lang.String, double, double, int, int)
	 */
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		Set<Tuple> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#zremrangeByRank(java.lang.String,
	 * int, int)
	 */
	public Long zremrangeByRank(String key, int start, int end) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zremrangeByRank(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zremrangeByScore(java.lang.
	 * String, double, double)
	 */
	public Long zremrangeByScore(String key, double start, double end) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zremrangeByScore(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#linsert(java.lang.String,
	 * redis.clients.jedis.BinaryClient.LIST_POSITION, java.lang.String,
	 * java.lang.String)
	 */
	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.linsert(key, where, pivot, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#set(byte[], byte[])
	 */
	public String set(byte[] key, byte[] value) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.set(key, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#get(byte[])
	 */
	public byte[] get(byte[] key) {
		byte[] result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.get(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#exists(byte[])
	 */
	public Boolean exists(byte[] key) {
		Boolean result = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.exists(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#type(byte[])
	 */
	public String type(byte[] key) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.type(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#expire(byte[], int)
	 */
	public Long expire(byte[] key, int seconds) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.expire(key, seconds);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#expireAt(byte[], long)
	 */
	public Long expireAt(byte[] key, long unixTime) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.expireAt(key, unixTime);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#ttl(byte[])
	 */
	public Long ttl(byte[] key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.ttl(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#getSet(byte[], byte[])
	 */
	public byte[] getSet(byte[] key, byte[] value) {
		byte[] result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.getSet(key, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#setnx(byte[], byte[])
	 */
	public Long setnx(byte[] key, byte[] value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.setnx(key, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#setex(byte[], int, byte[])
	 */
	public String setex(byte[] key, int seconds, byte[] value) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.setex(key, seconds, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#decrBy(byte[], long)
	 */
	public Long decrBy(byte[] key, long integer) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.decrBy(key, integer);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#decr(byte[])
	 */
	public Long decr(byte[] key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.decr(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#incrBy(byte[], long)
	 */
	public Long incrBy(byte[] key, long integer) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.incrBy(key, integer);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#incr(byte[])
	 */
	public Long incr(byte[] key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.incr(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#append(byte[], byte[])
	 */
	public Long append(byte[] key, byte[] value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.append(key, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#substr(byte[], int, int)
	 */
	public byte[] substr(byte[] key, int start, int end) {
		byte[] result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.substr(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hset(byte[], byte[], byte[])
	 */
	public Long hset(byte[] key, byte[] field, byte[] value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.hset(key, field, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hget(byte[], byte[])
	 */
	public byte[] hget(byte[] key, byte[] field) {
		byte[] result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.hget(key, field);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hsetnx(byte[], byte[],
	 * byte[])
	 */
	public Long hsetnx(byte[] key, byte[] field, byte[] value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.hsetnx(key, field, value);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hmset(byte[], java.util.Map)
	 */
	public String hmset(byte[] key, Map<byte[], byte[]> hash) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.hmset(key, hash);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hmget(byte[], byte)
	 */
	public List<byte[]> hmget(byte[] key, byte[]... fields) {
		List<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.hmget(key, fields);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hincrBy(byte[], byte[],
	 * long)
	 */
	public Long hincrBy(byte[] key, byte[] field, long value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.hincrBy(key, field, value);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hexists(byte[], byte[])
	 */
	public Boolean hexists(byte[] key, byte[] field) {
		Boolean result = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.hexists(key, field);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hdel(byte[], byte[])
	 */
	public Long hdel(byte[] key, byte[] field) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.hdel(key, field);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hlen(byte[])
	 */
	public Long hlen(byte[] key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.hlen(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hkeys(byte[])
	 */
	public Set<byte[]> hkeys(byte[] key) {
		Set<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.hkeys(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hvals(byte[])
	 */
	public Collection<byte[]> hvals(byte[] key) {
		Collection<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.hvals(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#hgetAll(byte[])
	 */
	public Map<byte[], byte[]> hgetAll(byte[] key) {
		Map<byte[], byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.hgetAll(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#rpush(byte[], byte[])
	 */
	public Long rpush(byte[] key, byte[] string) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.rpush(key, string);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#lpush(byte[], byte[])
	 */
	public Long lpush(byte[] key, byte[] string) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.lpush(key, string);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#llen(byte[])
	 */
	public Long llen(byte[] key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.llen(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#lrange(byte[], int, int)
	 */
	public List<byte[]> lrange(byte[] key, int start, int end) {
		List<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.lrange(key, start, end);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#ltrim(byte[], int, int)
	 */
	public String ltrim(byte[] key, int start, int end) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.ltrim(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#lindex(byte[], int)
	 */
	public byte[] lindex(byte[] key, int index) {
		byte[] result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.lindex(key, index);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#lset(byte[], int, byte[])
	 */
	public String lset(byte[] key, int index, byte[] value) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.lset(key, index, value);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#lrem(byte[], int, byte[])
	 */
	public Long lrem(byte[] key, int count, byte[] value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.lrem(key, count, value);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#lpop(byte[])
	 */
	public byte[] lpop(byte[] key) {
		byte[] result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.lpop(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#rpop(byte[])
	 */
	public byte[] rpop(byte[] key) {
		byte[] result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.rpop(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#sadd(byte[], byte[])
	 */
	public Long sadd(byte[] key, byte[] member) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.sadd(key, member);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#smembers(byte[])
	 */
	public Set<byte[]> smembers(byte[] key) {
		Set<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.smembers(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#srem(byte[], byte[])
	 */
	public Long srem(byte[] key, byte[] member) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.srem(key, member);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#spop(byte[])
	 */
	public byte[] spop(byte[] key) {
		byte[] result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.spop(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#scard(byte[])
	 */
	public Long scard(byte[] key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.scard(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#sismember(byte[], byte[])
	 */
	public Boolean sismember(byte[] key, byte[] member) {
		Boolean result = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.sismember(key, member);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#srandmember(byte[])
	 */
	public byte[] srandmember(byte[] key) {
		byte[] result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.srandmember(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zadd(byte[], double, byte[])
	 */
	public Long zadd(byte[] key, double score, byte[] member) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zadd(key, score, member);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrange(byte[], int, int)
	 */
	public Set<byte[]> zrange(byte[] key, int start, int end) {
		Set<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrange(key, start, end);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrem(byte[], byte[])
	 */
	public Long zrem(byte[] key, byte[] member) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrem(key, member);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zincrby(byte[], double,
	 * byte[])
	 */
	public Double zincrby(byte[] key, double score, byte[] member) {
		Double result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zincrby(key, score, member);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrank(byte[], byte[])
	 */
	public Long zrank(byte[] key, byte[] member) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrank(key, member);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrevrank(byte[], byte[])
	 */
	public Long zrevrank(byte[] key, byte[] member) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrank(key, member);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrevrange(byte[], int, int)
	 */
	public Set<byte[]> zrevrange(byte[] key, int start, int end) {
		Set<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrange(key, start, end);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrangeWithScores(byte[],
	 * int, int)
	 */
	public Set<Tuple> zrangeWithScores(byte[] key, int start, int end) {
		Set<Tuple> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrangeWithScores(key, start, end);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrevrangeWithScores(byte[],
	 * int, int)
	 */
	public Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end) {
		Set<Tuple> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrangeWithScores(key, start, end);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zcard(byte[])
	 */
	public Long zcard(byte[] key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zcard(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zscore(byte[], byte[])
	 */
	public Double zscore(byte[] key, byte[] member) {
		Double result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zscore(key, member);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#sort(byte[])
	 */
	public List<byte[]> sort(byte[] key) {
		List<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.sort(key);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#sort(byte[],
	 * redis.clients.jedis.SortingParams)
	 */
	public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
		List<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.sort(key, sortingParameters);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zcount(byte[], double,
	 * double)
	 */
	public Long zcount(byte[] key, double min, double max) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zcount(key, min, max);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrangeByScore(byte[],
	 * double, double)
	 */
	public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
		Set<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrangeByScore(key, min, max);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrangeByScore(byte[],
	 * double, double, int, int)
	 */
	public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
		Set<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrangeByScore(key, min, max, offset, count);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#zrangeByScoreWithScores(byte[],
	 * double, double)
	 */
	public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
		Set<Tuple> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrangeByScoreWithScores(key, min, max);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#zrangeByScoreWithScores(byte[],
	 * double, double, int, int)
	 */
	public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
		Set<Tuple> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrangeByScoreWithScores(key, min, max, offset, count);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrevrangeByScore(byte[],
	 * double, double)
	 */
	public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
		Set<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrangeByScore(key, max, min);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zrevrangeByScore(byte[],
	 * double, double, int, int)
	 */
	public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
		Set<byte[]> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrangeByScore(key, max, min, offset, count);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#zrevrangeByScoreWithScores(byte[]
	 * , double, double)
	 */
	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
		Set<Tuple> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrangeByScoreWithScores(key, max, min);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#zrevrangeByScoreWithScores(byte[]
	 * , double, double, int, int)
	 */
	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
		Set<Tuple> result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zremrangeByRank(byte[], int,
	 * int)
	 */
	public Long zremrangeByRank(byte[] key, int start, int end) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zremrangeByRank(key, start, end);

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#zremrangeByScore(byte[],
	 * double, double)
	 */
	public Long zremrangeByScore(byte[] key, double start, double end) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.zremrangeByScore(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#linsert(byte[],
	 * redis.clients.jedis.BinaryClient.LIST_POSITION, byte[], byte[])
	 */
	public Long linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {

			result = shardedJedis.linsert(key, where, pivot, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#pipelined(redis.clients.jedis.
	 * ShardedJedisPipeline)
	 */
	public List<Object> pipelined(ShardedJedisPipeline shardedJedisPipeline) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		List<Object> result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.pipelined(shardedJedisPipeline);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#getShard(byte[])
	 */
	public Jedis getShard(byte[] key) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		Jedis result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.getShard(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#getShard(java.lang.String)
	 */
	public Jedis getShard(String key) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		Jedis result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.getShard(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#getShardInfo(byte[])
	 */
	public JedisShardInfo getShardInfo(byte[] key) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		JedisShardInfo result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.getShardInfo(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zxtj.redis.impl.RedisClientTemplate#getShardInfo(java.lang.String)
	 */
	public JedisShardInfo getShardInfo(String key) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		JedisShardInfo result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.getShardInfo(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#getKeyTag(java.lang.String)
	 */
	public String getKeyTag(String key) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.getKeyTag(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#getAllShardInfo()
	 */
	public Collection<JedisShardInfo> getAllShardInfo() {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		Collection<JedisShardInfo> result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.getAllShardInfo();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zxtj.redis.impl.RedisClientTemplate#getAllShards()
	 */
	public Collection<Jedis> getAllShards() {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		Collection<Jedis> result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.getAllShards();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

}
