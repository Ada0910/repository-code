package com.ada.rabbitmq.api;

import com.ada.rabbitmq.api.util.RabbitMqProducerUtil;
import com.rabbitmq.client.Connection;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * API演示之生产者
 * <p/>
 *
 * @Date: 2022/12/3 23:36
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Producer {
	//交换机
	private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

	public static void main(String[] args) throws Exception {

		// 发送消息
		String msg = "Hello world, Rabbit MQ";
		// 建立连接
		Connection conn = RabbitMqProducerUtil.createConnection();
		// 创建消息通道
		RabbitMqProducerUtil.sendMessge(conn, msg, null, EXCHANGE_NAME, "ada.best", null, null);

	}
}
