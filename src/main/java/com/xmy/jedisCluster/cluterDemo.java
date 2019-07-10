package com.xmy.jedisCluster;

import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

public class cluterDemo {
    public static void main(String[] args) {
        List<String> hostPortList = new ArrayList<>();
        hostPortList.add("192.168.1.129:8006");
        hostPortList.add("192.168.1.129:8007");
        int timeout = 600;
        JedisClusterFactory jedisClusterFactory = new JedisClusterFactory(hostPortList, timeout);
        JedisCluster jedisCluster = jedisClusterFactory.getJedisCluster();
        String v = jedisCluster.get("hello");
        System.out.println(v);
        jedisClusterFactory.destroy();
    }

}
