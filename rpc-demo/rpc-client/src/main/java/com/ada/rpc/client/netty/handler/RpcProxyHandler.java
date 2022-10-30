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

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		response = msg;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("client exception is general");
	}
}
