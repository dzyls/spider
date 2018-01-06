package com.xing.spider.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {

	private static Jedis jedis;
	
	
	public static Jedis getJedis() {
		if (jedis == null || !jedis.isConnected()) {
			synchronized (RedisUtil.class) {
				if (jedis == null || !jedis.isConnected()) {
					jedis = new Jedis();
				}
			}
		}
		return jedis;
	}
	
}
