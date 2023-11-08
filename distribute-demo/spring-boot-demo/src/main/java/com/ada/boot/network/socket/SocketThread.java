package com.ada.boot.network.socket;

import java.net.Socket;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/5 17:29
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SocketThread implements Runnable {
	Socket socket;

	public SocketThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// 处理逻辑
	}
}
