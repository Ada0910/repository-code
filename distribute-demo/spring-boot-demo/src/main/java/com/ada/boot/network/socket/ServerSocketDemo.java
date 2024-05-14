package com.ada.boot.network.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 服务端
 * <p/>
 *
 * @Date: 2022/10/5 16:48
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ServerSocketDemo {
	public static void main(String[] args) throws Exception {

		 simpleSocketServer();


		//threadPoolSocketServer();


	}

	// 通过线程池来优化传统BIO的阻塞问题
	private static void threadPoolSocketServer() {
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		ServerSocket serverSocket = null;
		try {
			//服务端一定需要去监听一个端口号，ip默认就是本机的ip地址
			//ip:port
			serverSocket = new ServerSocket(9090);
			Socket socket = serverSocket.accept();
			executorService.execute(new SocketThread(socket));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 简单的服务器示例
	 */
	private static void simpleSocketServer() throws Exception {
		ServerSocket serverSocket = new ServerSocket(9090);

		Socket socket = serverSocket.accept();

		//拿到网络输入流
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		// 键盘输入流
		BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

		//网络输出流
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

		//获得输入流的信息
		System.out.println("Client:" + in.readLine());
		//获得控制台输入的数据
		String line = sin.readLine();
		// 键盘不敲bye
		while (!line.equals("bye")) {
			//写回到客户端
			printWriter.println(line);
			printWriter.flush();
			//读取客户端传过来的数据
			System.out.println("client:" + in.readLine());
			//重新读取控制台的数据
			line = sin.readLine();
		}

		//获得客户端的输入信息
		System.out.println(in.readLine());
	}
}
