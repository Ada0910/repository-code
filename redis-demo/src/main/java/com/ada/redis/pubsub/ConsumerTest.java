package com.ada.redis.pubsub;

import redis.clients.jedis.Jedis;


public class ConsumerTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("root");
        final MyListener listener = new MyListener();
        // 使用模式匹配的方式设置频道
        // 会阻塞
        jedis.psubscribe(listener, new String[]{"ada-*"});
    }
}
