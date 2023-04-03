package com.ada.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@EnableCircuitBreaker
@EnableHystrix
@EnableDiscoveryClient //暴露服务
@EnableFeignClients
@RestController
@SpringBootApplication
public class ServiceClientDemoApplication {

	//@LoadBalanced
	//private final RestTemplate restTemplate;
	//
	//@LoadBalanced
	//@Bean
	//public RestTemplate  getRestTemplate(){
	//	return new RestTemplate();
	//}

	//public ServiceClientDemoApplication(EchoServiceClient echoServiceClient,RestTemplate restTemplate) {
	//	this.echoServiceClient = echoServiceClient;
	//	this.restTemplate = restTemplate;
	//}

	public static void main(String[] args) {
		SpringApplication.run(ServiceClientDemoApplication.class, args);
	}

	public ServiceClientDemoApplication(EchoServiceClient echoServiceClient) {
			this.echoServiceClient = echoServiceClient;
		}
	

	private final EchoServiceClient echoServiceClient;

	@GetMapping(value = "/getName/")
	public String getName() {
		System.out.println(echoServiceClient.services());
		return echoServiceClient.services();
	}

	/**
	 *  熔断函数
	 */
	@HystrixCommand(fallbackMethod = "fallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "50"),
            })
	@GetMapping(value = "/call/echo/{message}")
	public String callEcho(@PathVariable String message) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "ECHO:" + message;
	}

	/**
	 *  熔断后的调用函数（参数需要跟原函数保持一致）
	 */
	public String fallback(String abc) {
		return "FALLBACK - " + abc;
	}
}
