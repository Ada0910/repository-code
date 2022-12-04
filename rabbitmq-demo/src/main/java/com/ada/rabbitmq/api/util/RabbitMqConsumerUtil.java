package com.ada.rabbitmq.api.util;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * RabbitMQ消费者工具类
 * <p/>
 *
 * @Date: 2022/12/3 23:48
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RabbitMqConsumerUtil {

	/**
	 *
	 * @Description: 获取连接
	 *
	 *
	 * @return com.rabbitmq.client.Connection
	 * @auther:xwn
	 * @date: 2022/12/4 12:06
	 */
	public static Connection getConnection() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		// 连接IP
		factory.setHost("127.0.0.1");
		// 默认监听端口
		factory.setPort(5672);
		// 虚拟机
		factory.setVirtualHost("/");

		// 设置访问的用户
		factory.setUsername("guest");
		factory.setPassword("guest");
		// 建立连接
		Connection conn = factory.newConnection();
		return conn;
	}

	/**
	 *
	 * @Description: 获取消息通道
	 *
	 * @param conn rabbitMQ连接对象
	 * @param exchangeName 交换机名称
	 * @param queueName 队列名称
	 * @param routingKey 路由键
	 *
	 * @return com.rabbitmq.client.Channel
	 * @auther:xwn
	 * @date: 2022/12/4 12:11
	 */
	public static Channel getChannel(Connection conn, String exchangeName, String queueName, String routingKey, Map<String, Object> exchangeArguments, Map<String, Object> queueArguments) throws Exception {
		// 创建消息通道
		Channel channel = conn.createChannel();
		// 声明交换机
		// String exchange, String type, boolean durable, boolean autoDelete, Map<String, Object> arguments
		channel.exchangeDeclare(exchangeName, "direct", false, false, exchangeArguments);
		// 声明队列
		// String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
		channel.queueDeclare(queueName, false, false, false, queueArguments);
		System.out.println(" Waiting for message....");
		// 绑定队列和交换机
		channel.queueBind(queueName, exchangeName, routingKey);
		return channel;
	}

	/**
	 *
	 * @Description: 获取消息并消费
	 *
	 * @param channel 消息信道
	 * @param queue 队列名称
	 *
	 * @auther:xwn
	 * @date: 2022/12/4 14:53
	 */
	public static void consumerMessage(Channel channel, String queue) throws Exception {
		// 创建消费者
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body, "UTF-8");
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				System.out.println("收到消息：[" + msg + "]\n接收时间：" + sf.format(new Date()));
				System.out.println("consumerTag : " + consumerTag);
				System.out.println("deliveryTag : " + envelope.getDeliveryTag());
				//这里可以对消息进行判断
				if (msg.contains("拒收")) {
					// 拒绝消息
					// requeue：是否重新入队列，true：是；false：直接丢弃，相当于告诉队列可以直接删除掉
					// TODO 如果只有这一个消费者，requeue 为true 的时候会造成消息重复消费
					channel.basicReject(envelope.getDeliveryTag(), false);
				} else if (msg.contains("异常")) {
					// 批量拒绝
					// requeue：是否重新入队列
					// TODO 如果只有这一个消费者，requeue 为true 的时候会造成消息重复消费
					channel.basicNack(envelope.getDeliveryTag(), true, false);
				} else {
					// 手工应答
					// 如果不应答，队列中的消息会一直存在，重新连接的时候会重复消费
					channel.basicAck(envelope.getDeliveryTag(), true);
				}
			}
		};
		// 开始获取消息
		// String queue, boolean autoAck, Consumer callback
		channel.basicConsume(queue, true, consumer);

	}


}
