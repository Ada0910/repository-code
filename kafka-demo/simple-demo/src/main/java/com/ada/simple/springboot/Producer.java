package com.ada.simple.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 基于spring boot 的生产者
 * <p/>
 *
 * @Date: 2023/3/14 20:14
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Component
public class Producer {

	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;

	public void send(){
		kafkaTemplate.send("test","key","Spring boot 对应的key");
	}
}
