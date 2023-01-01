package com.ada.redis.pubsub;

import redis.clients.jedis.Jedis;


/**
 * 测试类
 */
public class PublishTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("root");
        jedis.publish("ada-123", "666");
        jedis.publish("ada-abc", "pengyuyan");
    }
}
