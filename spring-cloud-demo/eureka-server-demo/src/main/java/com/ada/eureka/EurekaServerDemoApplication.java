package com.ada.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerDemoApplication.class, args);
	}

}
