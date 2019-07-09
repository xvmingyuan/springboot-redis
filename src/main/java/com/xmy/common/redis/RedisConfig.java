package com.xmy.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * 文件配置
 */
@Configuration
public class RedisConfig {
    @Autowired
    RedisConfigurationProperties redisConfigurationProperties;

    @Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> nodeSet = new HashSet<>();
        for (String node : redisConfigurationProperties.getNodes()) {
            String[] split = node.split(":");
            nodeSet.add(new HostAndPort(split[0], Integer.parseInt(split[1])));
        }
        return new JedisCluster(nodeSet);
    }

}
