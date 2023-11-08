package com.ada.simple.simple;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 *   生产者代码（用一个线程来实现）
 * <p/>
 *
 * @Date: 2023/3/12 18:36
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Producer extends Thread {

	private final KafkaProducer<Integer, String> producer;
	private final String topic;

	public Producer(String topic) {
		Properties properties = new Properties();

		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092 ");
		properties.put(ProducerConfig.CLIENT_ID_CONFIG, "practice-producer");
		// key的序列化方式
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		// value的序列化方式
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		producer = new KafkaProducer<Integer, String>(properties);
		// 自定义分片规则
		properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.ada.kafka.partitioner.MyPartioner");
		this.topic = topic;
	}


	//重写run方法

	@Override
	public void run() {
		int num = 0;
		while (num < 60) {
			String msg = "pratice test message:" + num;
			try {
				// 消息的发送（同步）
				producer.send(new ProducerRecord<>(topic, msg)).get();


				/*
				 * 本质上将，kafka都是采用异步的方式发送消息到broker中
				 *  （异步消息发送）
				 */
				producer.send(new ProducerRecord<>(topic, msg), new Callback() {
					@Override
					public void onCompletion(RecordMetadata recordMetadata, Exception e) {
						System.out.println("callback: " + recordMetadata.offset() + "->" + recordMetadata.partition());
					}
				});
				TimeUnit.SECONDS.sleep(2);
				num++;

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		// 主题
		new Producer("test").start();
	}
}
