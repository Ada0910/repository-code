package com.ada.rpc.client.simple;

import com.ada.api.simple.service.IHelloService;
import com.ada.rpc.client.simple.proxy.RpcProxyClient;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/18 18:31
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RpcSimpleClientStart {
	public static void main(String[] args) {
		RpcProxyClient rpcProxyClient = new RpcProxyClient();
		IHelloService iHelloService = rpcProxyClient.clientProxy(IHelloService.class, "localhost", 8080);
		String result = iHelloService.sayHello("ada");
		System.out.println(result);
	}
}
