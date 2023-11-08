package com.ada.rabbitmq.api;

import com.ada.rabbitmq.api.util.RabbitMqProducerUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 死信交换机-生产者
 * <p/>
 *
 * @Date: 2022/12/4 14:21
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class DlxProducer {
	public static void main(String[] args) throws Exception {
		Connection connection = RabbitMqProducerUtil.createConnection();
		String msg = "Hello world, Rabbit MQ, DLX MSG";
		// 设置属性，消息10秒钟过期
		AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2) // 持久化消息
				.contentEncoding("UTF-8").expiration("10000") // TTL
				.build();
		String routingKey = "TEST_DLX_QUEUE";
		RabbitMqProducerUtil.sendMessge(connection, msg, null, "", routingKey, "", properties);

	}
}
