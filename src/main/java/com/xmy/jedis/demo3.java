package com.xmy.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/**
　　* @Description: sentinelDemo
　　* @author xmy
　　* @date 2019-07-08 09:51
　　*/
public class demo3 {
    private static Logger logger = LoggerFactory.getLogger(demo3.class);

    public static void main(String[] args) {
        String masterName = "mymaster";
        Set<String> sentinelSet = new HashSet<String>();
        sentinelSet.add("it01:26379");
        sentinelSet.add("it02:26380");
        sentinelSet.add("it03:26381");
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisSentinelPool sentinelPool = new JedisSentinelPool(masterName, sentinelSet);
        Jedis jedis = null;
        int counter = 0;
        while (true) {
            counter++;
            try {
                jedis = sentinelPool.getResource();
                int index = new Random().nextInt(100000);
                String key = "k-" + index;
                String value = "v-" + index;
                jedis.set(key, value);
                if (counter % 100 == 0) {
                    logger.info("{} value is {}", key, jedis.get(key));
                }
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);

            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
    }
}
