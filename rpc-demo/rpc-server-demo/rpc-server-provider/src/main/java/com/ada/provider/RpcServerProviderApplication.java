package com.ada.provider;

import com.ada.provider.impl.HelloServiceImpl;
import com.ada.provider.proxy.RpcProxyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

// @SpringBootApplication
public class RpcServerProviderApplication {

	public static void main(String[] args) {
		// SpringApplication.run(RpcServerProviderApplication.class, args);
		HelloServiceImpl helloService = new HelloServiceImpl();
		RpcProxyServer rpcProxyServer = new RpcProxyServer();
		try {
			rpcProxyServer.publisher(helloService, 8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
