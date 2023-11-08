package com.ada.netty.tomcat.servlet;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * 自定义的servlet抽象类
 *
 * <p/>
 *
 * @Date: 2022/10/23 11:35
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public abstract class MyServlet {
	public void service(MyRequest request, MyResponse response) throws Exception {

		//由service方法来决定，是调用doGet或者调用doPost
		if ("GET".equalsIgnoreCase(request.getMethod())) {
			doGet(request, response);
		} else {
			doPost(request, response);
		}

	}

	public abstract void doGet(MyRequest request, MyResponse response) throws Exception;

	public abstract void doPost(MyRequest request, MyResponse response) throws Exception;
}
