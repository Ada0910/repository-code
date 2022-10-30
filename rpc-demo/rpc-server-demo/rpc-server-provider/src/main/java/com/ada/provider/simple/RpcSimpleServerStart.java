package com.ada.provider.simple;

import com.ada.api.simple.service.IHelloService;
import com.ada.provider.simple.impl.HelloServiceImpl;
import com.ada.provider.simple.proxy.RpcProxyServer;

import java.io.IOException;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 手写RPC启动类
 * <p/>
 *
 * @Date: 2022/10/18 18:11
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RpcSimpleServerStart {
	public static void main(String[] args) {
		IHelloService helloService = new HelloServiceImpl();
		RpcProxyServer rpcProxyServer = new RpcProxyServer();
		try {
			rpcProxyServer.publisher(helloService, 8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
