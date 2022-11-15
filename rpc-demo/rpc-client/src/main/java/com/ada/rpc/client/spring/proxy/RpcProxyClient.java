package com.ada.rpc.client.spring.proxy;



import com.ada.rpc.client.discovery.IServerDiscovery;
import com.ada.rpc.client.spring.handler.RemoteInvocationHandler;

import java.lang.reflect.Proxy;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 客户端的动态代理类
 * <p/>
 *
 * @Date: 2022/10/12 22:50
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RpcProxyClient {

	private IServerDiscovery serverDiscovery;

	// public RpcProxyClient(IServerDiscovery serverDiscovery) {
	// 	this.serverDiscovery = serverDiscovery;
	// }

	public RpcProxyClient() {
	}

	public <T> T clientProxy(final Class<T> interfaceClazz, final String host, final int port) {

		return (T) Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class<?>[]{interfaceClazz}, new RemoteInvocationHandler(host, port,serverDiscovery));
	}
}
