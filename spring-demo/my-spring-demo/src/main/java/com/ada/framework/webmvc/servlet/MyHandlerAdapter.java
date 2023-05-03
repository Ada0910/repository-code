package com.ada.framework.webmvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/5/3 21:19
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyHandlerAdapter {

	public boolean supports(Object handler) {
		return handler instanceof MyHandlerMapping;
	}

	public MyModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		return null;
	}
}
