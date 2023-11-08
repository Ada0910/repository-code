package com.ada.producer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ada.producer.mapper")
public class RabbitmqProducerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerDemoApplication.class, args);
	}

}
