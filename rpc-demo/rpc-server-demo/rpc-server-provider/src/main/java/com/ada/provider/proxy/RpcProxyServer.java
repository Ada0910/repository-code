package com.ada.provider.proxy;

import com.ada.provider.handler.ProcessorHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * RPC的代理类
 * <p/>
 *
 * @Date: 2022/10/10 23:44
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RpcProxyServer {

	// 线程池技术
	ExecutorService executorService = Executors.newCachedThreadPool();

	public void publisher(int port) throws IOException {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocket.accept();
				//每个socket是交个线程池来处理
				executorService.execute(new ProcessorHandler(socket));


			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}
		}
	}
}
