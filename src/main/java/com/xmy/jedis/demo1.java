package com.xmy.jedis;

import redis.clients.jedis.Jedis;

// 数据结构
public class demo1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("it01", 6379);
        d1(jedis);
        d2(jedis);
        d3(jedis);
        d4(jedis);
        d5(jedis);
        d6(jedis);
    }

    public static void d1(Jedis j) {
        j.set("hello", "hello world from mac jedis");
        String hello = j.get("hello");
        System.out.println(hello);
    }

    public static void d2(Jedis j) {
        System.out.println(j.incr("counter"));
    }

    public static void d3(Jedis j) {
        System.out.println(j.hset("myhash", "f1", "v1"));
        System.out.println(j.hset("myhash", "f2", "v2"));
        System.out.println(j.hgetAll("myhash"));
    }

    public static void d4(Jedis j) {
        System.out.println(j.rpush("mylist", "1"));
        System.out.println(j.rpush("mylist", "2"));
        System.out.println(j.rpush("mylist", "3"));
        System.out.println(j.lrange("mylist", 0, -1));
    }

    public static void d5(Jedis j) {
        System.out.println(j.sadd("myset", "a"));
        System.out.println(j.sadd("myset", "b"));
        System.out.println(j.sadd("myset", "a"));
        System.out.println(j.smembers("myset"));
    }

    public static void d6(Jedis j) {
        System.out.println(j.zadd("myzset", 1, "tom"));
        System.out.println(j.zadd("myzset", 2, "peter"));
        System.out.println(j.zadd("myzset", 3, "park"));
//        System.out.println(j.zrangeWithScores("myzset", 0, -1));
    }

    public static void ddddddd(Jedis j) {
        System.out.println(j);
    }
}
