package com.ada.rpc.client.trans;

import com.ada.api.request.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 传输对象
 * <p/>
 *
 * @Date: 2022/10/12 23:12
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RpcNetTransport {
	private String host;
	private int port;

	public RpcNetTransport(String host, int port) {
		this.host = host;
		this.port = port;
	}

	// 发送对象
	public Object send(RpcRequest request) {
		Socket socket= null;
		Object result = null;

		ObjectOutputStream outputStream = null;
		ObjectInputStream inputStream = null;

		try {
			socket = new Socket(host, port);

			//网络socket
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			//序列化()
			outputStream.writeObject(request);
			outputStream.flush();

			inputStream = new ObjectInputStream(socket.getInputStream());
			result = inputStream.readObject();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
