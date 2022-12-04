package com.ada.rabbitmq.api;

import com.ada.rabbitmq.api.util.RabbitMqProducerUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/12/4 13:50
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class TTLProducer {
	public static void main(String[] args) throws Exception {

		// 建立连接
		Connection conn = RabbitMqProducerUtil.createConnection();


		String msg = "Hello world, Rabbit MQ, DLX MSG";

		// 通过队列属性设置消息过期时间
		Map<String, Object> argss = new HashMap<String, Object>();
		argss.put("x-message-ttl", 6000);

		// 对每条消息设置过期时间
		AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2) // 持久化消息
				.contentEncoding("UTF-8").expiration("10000") // TTL
				.build();

		// 此处两种方式设置消息过期时间的方式都使用了，将以较小的数值为准
		RabbitMqProducerUtil.sendMessge(conn, msg, argss, "", "", "TEST_DLX_QUEUE", properties);
	}
}
