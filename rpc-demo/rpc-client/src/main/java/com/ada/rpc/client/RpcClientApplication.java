package com.ada.rpc.client;

import com.ada.api.service.IHelloService;
import com.ada.rpc.client.proxy.RpcProxyClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
public class RpcClientApplication {

	public static void main(String[] args) {

		// SpringApplication.run(RpcClientApplication.class, args);
		RpcProxyClient rpcProxyClient = new RpcProxyClient();
		IHelloService iHelloService = rpcProxyClient.clientProxy(IHelloService.class, "localhost", 8080);
		String result = iHelloService.sayHello("ada");
		System.out.println(result);
	}

}
