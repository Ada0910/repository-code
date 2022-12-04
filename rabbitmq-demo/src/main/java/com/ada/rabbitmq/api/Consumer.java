package com.ada.rabbitmq.api;

import com.ada.rabbitmq.api.util.RabbitMqConsumerUtil;
import com.rabbitmq.client.Channel;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * API演示之消费者
 * <p/>
 *
 * @Date: 2022/12/3 23:36
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Consumer {

	//交换机
	private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";
	// 队列
	private final static String QUEUE_NAME = "SIMPLE_QUEUE";

	//绑定的路由键
	static String bindingKey = "ada.best";


	public static void main(String[] args) throws Exception {
		// 建立消息通道
		Channel channel = RabbitMqConsumerUtil.getChannel(RabbitMqConsumerUtil.getConnection(), EXCHANGE_NAME, QUEUE_NAME, bindingKey, null, null);
		RabbitMqConsumerUtil.consumerMessage(channel, QUEUE_NAME);
	}
}
