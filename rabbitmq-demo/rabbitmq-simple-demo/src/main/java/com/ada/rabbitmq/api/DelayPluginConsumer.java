package com.ada.rabbitmq.api;

import com.ada.rabbitmq.api.util.RabbitMqConsumerUtil;
import com.rabbitmq.client.Channel;
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
 * @Date: 2022/12/4 14:47
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class DelayPluginConsumer {
	public static void main(String[] args) throws Exception {
		Connection conn = RabbitMqConsumerUtil.getConnection();
		// 声明x-delayed-message类型的exchange
		Map<String, Object> argss = new HashMap<String, Object>();
		argss.put("x-delayed-type", "direct");
		Channel channel = RabbitMqConsumerUtil.getChannel(conn, "DELAY_EXCHANGE", "DELAY_QUEUE", "DELAY_KEY", argss, null);
		RabbitMqConsumerUtil.consumerMessage(channel, "DELAY_QUEUE");
	}
}
