package com.ada.provider.handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/10 23:54
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ProcessorHandler implements Runnable {
	private Socket socket;

	public ProcessorHandler(Socket socket) {
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * 需要处理流的问题
	 */
	@Override
	public void run() {
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			// 输入流有什么东西
			// TODO
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
