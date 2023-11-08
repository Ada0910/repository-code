package com.ada.boot.network.serial;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 客户端
 * <p/>
 *
 * @Date: 2022/10/6 18:25
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SocketClient {
	public static void main(String[] args) {

		Socket socket = null;
		ObjectOutputStream out = null;
		try {
			socket = new Socket("127.0.0.1", 8080);
			User user = new User();
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(user);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
