package com.ada.simple.springboot;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 基于spring boot 的消费者
 * <p/>
 *
 * @Date: 2023/3/14 20:14
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Component
public class Consumer {

	@KafkaListener(topics = {"test"})
	public void listener(ConsumerRecord record) {
		Optional<?> msg = Optional.ofNullable(record.value());
		if(msg.isPresent()){
			System.out.println(msg.get());
		}
	}
}
