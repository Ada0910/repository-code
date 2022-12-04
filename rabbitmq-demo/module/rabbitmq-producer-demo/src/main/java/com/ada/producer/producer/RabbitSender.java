package com.ada.producer.producer;

import com.ada.producer.entity.Merchant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:config.properties")
public class RabbitSender {

	@Value("${com.ada.directexchange}")
	private String directExchange;

	@Value("${com.ada.topicexchange}")
	private String topicExchange;

	@Value("${com.ada.fanoutexchange}")
	private String fanoutExchange;

	@Value("${com.ada.directroutingkey}")
	private String directRoutingKey;

	@Value("${com.ada.topicroutingkey1}")
	private String topicRoutingKey1;

	@Value("${com.ada.topicroutingkey2}")
	private String topicRoutingKey2;


	// 自定义的模板，所有的消息都会转换成JSON发送
	@Autowired
	AmqpTemplate adaTemplate;

	public void send() throws JsonProcessingException {
		Merchant merchant = new Merchant(1001, "a direct msg : 中原镖局", "汉中省解放路266号");
		adaTemplate.convertAndSend(directExchange, directRoutingKey, merchant);

		adaTemplate.convertAndSend(topicExchange, topicRoutingKey1, "a topic msg : shanghai.ada.teacher");
		adaTemplate.convertAndSend(topicExchange, topicRoutingKey2, "a topic msg : changsha.ada.student");

		// 发送JSON字符串
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(merchant);
		System.out.println(json);
		adaTemplate.convertAndSend(fanoutExchange, "", json);
	}


}
