package com.ada.netty.tomcat;

import com.ada.netty.tomcat.servlet.MyRequest;
import com.ada.netty.tomcat.servlet.MyResponse;
import com.ada.netty.tomcat.servlet.MyServlet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 业务处理类
 * <p/>
 *
 * @Date: 2022/10/23 12:24
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyTomcatHandler extends ChannelInboundHandlerAdapter {
	private Map<String, MyServlet> servletMapping = new HashMap<String, MyServlet>();

	public MyTomcatHandler(Map<String, MyServlet> servletMapping) {
		this.servletMapping = servletMapping;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof HttpRequest) {
			HttpRequest req = (HttpRequest) msg;
			// 转交给我们自己的request实现
			MyRequest request = new MyRequest(ctx, req);
			// 转交给我们自己的response实现
			MyResponse response = new MyResponse(ctx, req);

			String url = request.getUrl();

			if (servletMapping.containsKey(url)) {
				servletMapping.get(url).service(request, response);
			} else {
				response.write("404 not found!!");
			}

		}
	}
}
