package com.ada.rpc.client.netty.proxy;

import com.ada.api.netty.trans.InvokerProtocol;
import com.ada.rpc.client.netty.handler.RpcProxyHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 方法代理
 * <p/>
 *
 * @Date: 2022/10/30 20:15
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MethodProxy implements InvocationHandler {
	private Class<?> clazz;

	public MethodProxy(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 将本地调用通过代理，转化成网络调用
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 判断是否是个实现类
		if (Object.class.equals(method.getDeclaringClass())) {
			return method.invoke(this, args);
		} else {
			// 接口
			return rpcInvoke(proxy, method, args);
		}
	}

	/**
	 * RPC调用
	 */
	private Object rpcInvoke(Object proxy, Method method, Object[] args) {
		//传输协议封装
		InvokerProtocol msg = new InvokerProtocol();
		msg.setClassName(this.clazz.getName());
		msg.setMethodName(method.getName());
		msg.setValues(args);
		msg.setParames(method.getParameterTypes());


		final RpcProxyHandler consumerHandler = new RpcProxyHandler();

		// 发起网络请求
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					//自定义协议解码器
					/** 入参有5个，分别解释如下
					 maxFrameLength：框架的最大长度。如果帧的长度大于此值，则将抛出TooLongFrameException。
					 lengthFieldOffset：长度字段的偏移量：即对应的长度字段在整个消息数据中得位置
					 lengthFieldLength：长度字段的长度：如：长度字段是int型表示，那么这个值就是4（long型就是8）
					 lengthAdjustment：要添加到长度字段值的补偿值
					 initialBytesToStrip：从解码帧中去除的第一个字节数
					 */
					// 下面两个是对数据的解析
					pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
					//自定义协议编码器
					pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
					//对象参数类型编码器（反序列化）
					pipeline.addLast("encoder", new ObjectEncoder());
					//对象参数类型解码器
					pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
					pipeline.addLast("handler", consumerHandler);
				}
			});

			ChannelFuture future = b.connect("localhost", 8080).sync();
			future.channel().writeAndFlush(msg).sync();
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
		return consumerHandler.getResponse();
	}

}

