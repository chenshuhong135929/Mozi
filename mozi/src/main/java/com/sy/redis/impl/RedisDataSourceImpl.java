package com.sy.redis.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sy.redis.RedisDataSource;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {
	private static final Logger log = Logger.getLogger(RedisDataSourceImpl.class);

	@Autowired
	private ShardedJedisPool shardedJedisPool;

	/**
	 * @return ShardedJedis <获取redis客户端连接，执行命令>
	 */
	public ShardedJedis getRedisClient() {
		try {
			ShardedJedis shardedJedis = shardedJedisPool.getResource();
			return shardedJedis;
		} catch (Exception e) {
			log.error("获取redis客户端连接失败", e);
		}
		return null;
	}

	/**
	 * 
	 * @param shardedJedis
	 *            <将资源返还个pool>
	 */
	public void returnResource(ShardedJedis shardedJedis) {

		shardedJedisPool.returnResource(shardedJedis);

	}

	/**
	 * 
	 * @param shardedJedis
	 *            <出现异常后，将资源返还给pool>
	 */
	public void returnResource(ShardedJedis shardedJedis, boolean broken) {

		if (broken) {
			shardedJedisPool.returnBrokenResource(shardedJedis);
		} else {
			shardedJedisPool.returnResource(shardedJedis);
		}

	}

}
