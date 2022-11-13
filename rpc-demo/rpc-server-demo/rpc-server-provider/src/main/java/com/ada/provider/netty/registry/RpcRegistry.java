package com.ada.provider.netty.registry;

import com.ada.provider.netty.handler.RegistryHandler;
import com.ada.provider.registry.IRegistryCenter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * RPC实现的注册中心（使用netty的API来实现）
 * <p/>
 *
 * @Date: 2022/10/30 18:40
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RpcRegistry {


	// 端口号
	private final int port;

	public RpcRegistry(int port) {
		this.port = port;
	}

	/**
	 * 启动方法
	 */
	public static void main(String[] args) {
		new RpcRegistry(8080).start();
	}

	/**
	 * 启动方法
	 */
	private void start() {
		// server初始化
		// 主线程初始化
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// work线程初始化
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

				// 客户端连接过来之后，就会调用这个方法，把所有业务的处理逻辑归总到一个队列中
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					//自定义协议解码器
					/** 入参有5个，分别解释如下
					 maxFrameLength：框架的最大长度。如果帧的长度大于此值，则将抛出TooLongFrameException。
					 lengthFieldOffset：长度字段的偏移量：即对应的长度字段在整个消息数据中得位置
					 lengthFieldLength：长度字段的长度。如：长度字段是int型表示，那么这个值就是4（long型就是8）
					 lengthAdjustment：要添加到长度字段值的补偿值
					 initialBytesToStrip：从解码帧中去除的第一个字节数
					 */

					// 下面两个是还原InvokerProtocol对象
					// 因为是自定义的协议，所以需要编码和解码（数据的解析）
					pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
					//自定义协议编码器
					pipeline.addLast(new LengthFieldPrepender(4));
					//对象参数类型编码器（下面两个是还原形参和实参）
					pipeline.addLast("encoder", new ObjectEncoder());
					//对象参数类型解码器
					pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
					//执行属于自己的逻辑
					pipeline.addLast(new RegistryHandler());
				}
			}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
			// 线程异步回调（启动服务，相当于开始轮询）
			ChannelFuture future = b.bind(port).sync();
			System.out.println("My Netty RPC Registry start listen at " + port);
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
