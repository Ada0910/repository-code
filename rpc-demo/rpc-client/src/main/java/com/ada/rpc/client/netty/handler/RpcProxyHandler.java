package com.ada.rpc.client.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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
public class RpcProxyHandler extends ChannelInboundHandlerAdapter {
	private Object response;

	public Object getResponse() {
		return response;
	}

	/**
	 * 有客户端连上的时候，就会触发，回调
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		response = msg;
	}

	/**
	 * 有连接异常的时候回触发
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("client exception is general");
	}
}
