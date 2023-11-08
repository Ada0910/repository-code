package com.ada.netty.tomcat;

import com.ada.netty.tomcat.servlet.MyServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 *  简单的实现一个Tomcat（最最最最基础版本-------- ）
 *
 * <p/>
 *
 * @Date: 2022/10/23 11:24
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyTomcat {
	//端口号
	private int port;

	private Map<String, MyServlet> servletMapping = new HashMap<String, MyServlet>();

	// 配置文件
	private Properties webxml = new Properties();

	public MyTomcat(int port) {
		this.port = port;
	}

	/**
	 * main 方法（主线程）
	 */
	public static void main(String[] args) {
		new MyTomcat(8080).start();
	}

	/**
	 * 启动方法
	 */
	private void start() {
		// 初始化配置
		initConfig();

		//Netty方式启动
		runWithNetty();
	}


	/**
	 * 初始化配置
	 */
	private void initConfig() {
		//加载web.xml文件,同时初始化 ServletMapping对象
		try {
			String WEB_INF = this.getClass().getResource("/").getPath();
			FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");

			webxml.load(fis);

			// 初始化servletMapping
			for (Object k : webxml.keySet()) {
				String key = k.toString();
				if (key.endsWith(".url")) {
					// 如：servlet.one
					String servletName = key.replaceAll("\\.url$", "");
					// /firstServlet.do
					String url = webxml.getProperty(key);
					//com.ada.netty.tomcat.http.FirstServlet
					String className = webxml.getProperty(servletName + ".className");
					// 反射实例化
					MyServlet obj = (MyServlet) Class.forName(className).newInstance();
					servletMapping.put(url, obj);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Netty方式启动
	 */
	private void runWithNetty() {
		// Boss线程(selector)
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// Worker线程
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			//ServetBootstrap   ServerSocketChannel
			ServerBootstrap server = new ServerBootstrap();

			server.group(bossGroup, workerGroup)
					// 主线程处理类,看到这样的写法，底层就是用反射
					.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
						// 客户端初始化处理
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							// 无锁化串行编程
							//Netty对HTTP协议的封装，顺序有要求
							// HttpResponseEncoder 编码器
							socketChannel.pipeline().addLast(new HttpResponseEncoder());
							// HttpRequestDecoder 解码器
							socketChannel.pipeline().addLast(new HttpRequestDecoder());
							// 业务逻辑处理
							socketChannel.pipeline().addLast(new MyTomcatHandler(servletMapping));
						}
						// 针对主线程的配置 分配线程最大数量 128
					}).option(ChannelOption.SO_BACKLOG, 128)
					// 针对子线程的配置 保持长连接
					.childOption(ChannelOption.SO_KEEPALIVE, true);

			//启动服务器
			ChannelFuture sync = server.bind(port).sync();
			System.out.println("MyTomcat 已启动，监听的端口是：" + port);
			sync.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 关闭线程池
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}
}
