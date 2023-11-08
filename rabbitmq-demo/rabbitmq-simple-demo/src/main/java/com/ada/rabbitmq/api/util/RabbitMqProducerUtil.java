package com.ada.rabbitmq.api.util;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Map;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * RabbitMQ生产者工具类
 * <p/>
 *
 * @Date: 2022/12/4 12:20
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RabbitMqProducerUtil {
	/**
	 *
	 * @Description: 获取连接
	 *
	 * @return com.rabbitmq.client.Connection
	 * @auther:xwn
	 * @date: 2022/12/4 12:06
	 */
	public static Connection createConnection() throws Exception {
		// 方式一：直接填参数
		ConnectionFactory factory = new ConnectionFactory();

		// 方式一：直接填参数
		// 连接IP
		factory.setHost("127.0.0.1");
		// 默认监听端口
		factory.setPort(5672);
		// 虚拟机
		factory.setVirtualHost("/");

		// 设置访问的用户
		factory.setUsername("guest");
		factory.setPassword("guest");

		// 方式二：做成配置化
		// factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

		// 建立连接
		Connection conn = factory.newConnection();
		return conn;
	}

	/**
	 *
	 * @Description:发送消息
	 *
	 * @param conn 连接
	 * @param msg F阿松消息
	 * @param queueArguments 队列属性
	 * @param exchange 交换机
	 * @param routingKey 路由键
	 * @param queue 队列
	 * @param properties 其他属性
	 *
	 * @auther:xwn
	 * @date: 2022/12/4 14:09
	 */
	public static void sendMessge(Connection conn, String msg, Map<String, Object> queueArguments, String exchange, String routingKey, String queue, AMQP.BasicProperties properties) throws Exception {
		// 创建消息通道
		Channel channel = conn.createChannel();

		// 声明队列（默认交换机AMQP default，Direct）
		// String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
		channel.queueDeclare(queue, false, false, false, queueArguments);

		// 发送消息
		channel.basicPublish(exchange, routingKey, properties, msg.getBytes());
		channel.close();
		conn.close();
	}


}
