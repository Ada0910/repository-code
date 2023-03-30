package com.ada.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@EnableFeignClients
@RestController
@SpringBootApplication
public class ServiceClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceClientDemoApplication.class, args);
	}

	public ServiceClientDemoApplication(EchoServiceClient echoServiceClient) {
		this.echoServiceClient = echoServiceClient;
	}

	private final EchoServiceClient echoServiceClient;

	@GetMapping(value = "/call/echo/{message}")
	public String callEcho(@PathVariable String message) {
		//return echoServiceClient.echo(message);
		System.out.println(echoServiceClient.services());
		return echoServiceClient.services();
	}

}
