package com.xmy.jedisCluster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JedisClusterFactory {

    private JedisCluster jedisCluster;
    private List<String> hostPortList;
    private int timeout;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public JedisClusterFactory() {
    }

    public JedisClusterFactory(List<String> hostPortList, int timeout) {
        this.hostPortList = hostPortList;
        this.timeout = timeout;
        init();
    }


    public void init() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        Set<HostAndPort> nodes = new HashSet<>();
        for (String node : hostPortList) {
            String[] split = node.split(":");
            if (split.length != 2) {
                continue;
            }
            nodes.add(new HostAndPort(split[0], Integer.parseInt(split[1])));
        }
        try {
            jedisCluster = new JedisCluster(nodes, timeout, jedisPoolConfig);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        }
    }

    public void destroy() {
        if (jedisCluster != null) {
            try {
                jedisCluster.close();
            } catch (IOException e) {
                logger.info(e.getMessage(), e);
            }
        }
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setHostPortList(List<String> hostPortList) {
        this.hostPortList = hostPortList;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
