package com.ada.rpc.client.proxy;

import com.ada.rpc.client.handler.RemoteInvocationHandler;

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
	public <T> T clientProxy(final Class<T> interfaceClazz, final String host, final int port) {

		return (T) Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class<?>[]{interfaceClazz}, new RemoteInvocationHandler(host, port));
	}
}
