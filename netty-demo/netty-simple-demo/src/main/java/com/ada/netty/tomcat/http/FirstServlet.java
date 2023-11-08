package com.ada.netty.tomcat.http;

import com.ada.netty.tomcat.servlet.MyRequest;
import com.ada.netty.tomcat.servlet.MyResponse;
import com.ada.netty.tomcat.servlet.MyServlet;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/23 11:47
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class FirstServlet extends MyServlet {
	@Override
	public void doGet(MyRequest request, MyResponse response) throws Exception {
		this.doPost(request, response);
	}

	@Override
	public void doPost(MyRequest request, MyResponse response) throws Exception {
		response.write("This is First Serlvet");
	}
}
