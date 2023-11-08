package com.ada.netty.io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 同步阻塞IO 客户端
 *
 * 同步：统一时间只能读或者写
 * 阻塞：流处理
 *
 * <p/>
 *
 * @Date: 2022/10/19 22:57
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class BIOClient {

	public static void main(String[] args) throws UnknownHostException, IOException {

		//要和谁进行通信，服务器IP、服务器的端口
		//一台机器的端口号是有限
		Socket client = new Socket("localhost", 8080);

		//输出 O  write();
		//不管是客户端还是服务端，都有可能write和read

		OutputStream os = client.getOutputStream();

		//生成一个随机的ID
		String name = UUID.randomUUID().toString();

		System.out.println("客户端发送数据：" + name);
		//传说中的101011010
		os.write(name.getBytes());
		os.close();
		client.close();


	}


}
