package com.ada.rpc.client.spring;

import com.ada.api.service.IHelloService;
import com.ada.rpc.client.spring.config.SpringConfig;
import com.ada.rpc.client.spring.proxy.RpcProxyClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/18 18:32
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RpcSpringClientStart {
	public static void main(String[] args) {
		// 方法二：
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		RpcProxyClient rpcProxyClient = context.getBean(RpcProxyClient.class);
		IHelloService iHelloService = rpcProxyClient.clientProxy(IHelloService.class, "localhost", 8080);
		String result2 = iHelloService.sayHello("ada");
		System.out.println(result2);

	}
}
