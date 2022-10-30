package com.ada.rpc.client.spring.handler;

import com.ada.api.simple.request.RpcRequest;
import com.ada.rpc.client.trans.RpcNetTransport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/12 22:57
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RemoteInvocationHandler implements InvocationHandler {

	private String host;

	private int port;

	public RemoteInvocationHandler(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 请求会进入这里
		RpcRequest rpcRequest = new RpcRequest();
		rpcRequest.setClassName(method.getDeclaringClass().getName());
		rpcRequest.setMethodName(method.getName());
		rpcRequest.setParameters(args);
		rpcRequest.setVersion("2.0");
		System.out.println(method.getDeclaringClass().getName()+":"+method.getName()+":"+args);
		// 通过网络传输
		RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
		Object object = rpcNetTransport.send(rpcRequest);
		return object;
	}
}
