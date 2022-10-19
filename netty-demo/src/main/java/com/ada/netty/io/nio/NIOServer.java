package com.ada.netty.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * NIO服务端：非阻塞同步IO
 *
 * 同步：统一时间只能读或者写
 * 非阻塞：通道处理
 *
 * <p/>
 *
 * @Date: 2022/10/19 23:09
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class NIOServer {

	// 端口
	private int port;

	//轮询器Selector
	private Selector selector;

	//缓存区，Buffer
	private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

	// 处理方法
	private void listen() {
		System.out.println("listen on " + this.port + ".");
		while (true) {

			try {
				// 轮询
				selector.select();

				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();
				// 同步体现在这里，因为每次只能拿到一个KEY，只能处理一种状态
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					iterator.remove();
					// 每个key代表一种状态，通过对于处理每种状态的数据来实现非阻塞
					process(key);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 对于不同状态的KEy的进行处理，c从而实现非阻塞
	 */
	private void process(SelectionKey key) throws Exception {

		// 准备就绪状态
		if (key.isAcceptable()) {
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel channel = server.accept();
			// 设置成非阻塞状态
			channel.configureBlocking(false);
			// 修改key的状态,就绪的时候，改变状态换成是可读的状态
			key = channel.register(selector, SelectionKey.OP_READ);

			// key可读状态
		} else if (key.isReadable()) {
			//key.channel 从多路复用器中拿到客户端的引用
			SocketChannel channel = (SocketChannel) key.channel();
			int len = channel.read(byteBuffer);
			if (len > 0) {
				byteBuffer.flip();
				// 读数据
				String content = new String(byteBuffer.array(), 0, len);
				channel.register(selector, SelectionKey.OP_READ);
				System.out.println("读取内容：" + content);
			}

			// key是可写状态
		} else if (key.isWritable()) {
			SocketChannel channel = (SocketChannel) key.channel();
			// 读取的内容
			String content = (String) key.attachment();
			channel.write(ByteBuffer.wrap(("输出：" + content).getBytes()));
			channel.close();
		}

	}


	/**
	 * 1.构造方法，初始化
	 */
	public NIOServer(int port) {
		this.port = port;
		try {
			// 1.打开通道
			ServerSocketChannel server = ServerSocketChannel.open();
			// 地址（IP，端口号），进行绑定
			server.bind(new InetSocketAddress(this.port));
			// NIO模型默认是采用阻塞的(false就是非阻塞的)
			server.configureBlocking(false);

			// 2.轮询器初始化
			selector = Selector.open();
			// 通道上注册轮询器
			server.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new NIOServer(8080).listen();
	}


}
