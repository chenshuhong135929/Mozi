package com.sy.redis;

import redis.clients.jedis.ShardedJedis;

public interface RedisDataSource {
	/**
	 * @return ShardedJedis <获取redis客户端连接，执行命令>
	 */
	public ShardedJedis getRedisClient();

	/**
	 * @param shardedJedis
	 *            <将资源返还个pool>
	 */
	public void returnResource(ShardedJedis shardedJedis);

	/**
	 * @param shardedJedis
	 *            <出现异常后，将资源返还给pool>
	 */
	public void returnResource(ShardedJedis shardedJedis, boolean broken);
}
