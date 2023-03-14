package com.ada.kafka;

import com.ada.kafka.springboot.Producer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaDemoApplication {

	public static void main(String[] args) throws InterruptedException {

		ConfigurableApplicationContext context = SpringApplication.run(KafkaDemoApplication.class, args);
		Producer p = context.getBean(Producer.class);
		for (int i = 0; i < 10; i++) {
			p.send();
			TimeUnit.SECONDS.sleep(2);
		}
	}

}
