package com.ada.netty.tomcat.servlet;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 *  包装输入流
 *
 * <p/>
 *
 * @Date: 2022/10/23 11:38
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyRequest {
	//SocketChannel的封装
	private ChannelHandlerContext ctx;

	private HttpRequest req;

	public MyRequest(ChannelHandlerContext ctx, HttpRequest req) {
		this.ctx = ctx;
		this.req = req;
	}

	public String getMethod() {
		return req.method().name();
	}

	public String getUrl() {
		return req.uri();
	}
}
