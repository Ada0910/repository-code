package com.ada.rpc.client.netty;

import com.ada.api.netty.api.IRpcHelloService;
import com.ada.api.netty.api.IRpcService;
import com.ada.rpc.client.netty.proxy.RpcProxy;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 客户端启动
 * <p/>
 *
 * @Date: 2022/10/30 19:06
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RpcNettyClientStart {
	public static void main(String[] args) {
		IRpcHelloService rpcHello = RpcProxy.create(IRpcHelloService.class);

		System.out.println(rpcHello.hello("IKUN 集合了！"));

		IRpcService service = RpcProxy.create(IRpcService.class);

		System.out.println("8 + 2 = " + service.add(8, 2));
		System.out.println("8 - 2 = " + service.sub(8, 2));
		System.out.println("8 * 2 = " + service.mult(8, 2));
		System.out.println("8 / 2 = " + service.div(8, 2));
	}
}
