package com.ada.distribute.network.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 客户端
 * <p/>
 *
 * @Date: 2022/10/5 16:50
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ClientDemo {
	public static void main(String[] args) {
		try {
			//找到目标的ip和端口
			Socket socket = new Socket("localhost", 9090);
			//在当前链接上写入输入
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			//控制台的输入流
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			//网络输入流
			BufferedReader in=new BufferedReader(new InputStreamReader
					(socket.getInputStream()));

			// 获取键盘上的输入
			String readLine = sin.readLine();
			while(!readLine.equals("bye")){
				out.println(readLine);

				System.out.println("Server:"+in.readLine());
				//重新获取
				readLine=sin.readLine();
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
