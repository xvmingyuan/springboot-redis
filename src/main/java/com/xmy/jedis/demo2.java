package com.xmy.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
　　* @Description: jedis连接池
　　* @author xmy
　　* @date 2019-07-08 09:50
　　*/
public class demo2 {
    private static GenericObjectPoolConfig poolConfig;
    private static JedisPool jedisPool;
    private static Jedis jedis = null;

    static JedisPool createPool() {
        poolConfig = new GenericObjectPoolConfig();
        jedisPool = new JedisPool(poolConfig, "it01", 6379);
        return jedisPool;
    }

    public static void main(String[] args) {
        try {
            jedis = createPool().getResource();
            jedis.set("pool", "hello world from mac");
            System.out.println(jedis.get("pool"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

    }

    public static void ddddddd(Jedis j) {
        System.out.println(j);
    }
}
