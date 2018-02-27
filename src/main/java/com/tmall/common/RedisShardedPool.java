package com.tmall.common;

import com.sun.istack.internal.NotNull;
import com.tmall.util.PropertiesUtil;
import redis.clients.jedis.*;
import redis.clients.util.Hashing;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiuxin
 */
public class RedisShardedPool {


    private static ShardedJedisPool pool;

    /**
     * 最大连接数
     */
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total", "200"));

    /**
     * 在JedisPool中最大idle状态Jedis实例个数
     */
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle", "20"));

    /**
     * JedisPool中最小idle状态Jedis实例个数
     */
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle", "20"));

    /**
     * borrow一个Jedis实例时是否进行校验，true则得到的实例肯定是可用的
     */
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow", "true"));

    /**
     * return一个Jedis实例时是否进行校验，true则放回的实例肯定是可用的
     */
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "true"));

    private static String redis1Ip = PropertiesUtil.getProperty("redis1.ip");
    private static String redis2Ip = PropertiesUtil.getProperty("redis2.ip");

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setBlockWhenExhausted(true);

        JedisShardInfo jedisShardInfo1 = new JedisShardInfo(redis1Ip, 6379, 2 * 1000);
        JedisShardInfo jedisShardInfo2 = new JedisShardInfo(redis2Ip, 6380, 2 * 1000);
        List<JedisShardInfo> jedisShardInfoList = new ArrayList<>(2);
        jedisShardInfoList.add(jedisShardInfo1);
        jedisShardInfoList.add(jedisShardInfo2);

        pool = new ShardedJedisPool(config, jedisShardInfoList, Hashing.MURMUR_HASH, ShardedJedis.DEFAULT_KEY_TAG_PATTERN);
    }

    static {
        initPool();
    }

    public static ShardedJedis getJedis() {
        return pool.getResource();
    }

    public static void returnBrokenResource(ShardedJedis jedis) {
        pool.returnBrokenResource(jedis);
    }
    public static void returnResource(ShardedJedis jedis) {
        pool.returnResource(jedis);
    }
}
