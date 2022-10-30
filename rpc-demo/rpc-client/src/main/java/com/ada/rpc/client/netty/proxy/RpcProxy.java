package com.ada.rpc.client.netty.proxy;

import java.lang.reflect.Proxy;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/30 19:08
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RpcProxy {


	public static <T> T
	create(Class<?> clazz) {
		MethodProxy proxy = new MethodProxy(clazz);
		Class<?>[] interfaces = clazz.isInterface() ? new Class[]{clazz} : clazz.getInterfaces();
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, proxy);
	}
}
