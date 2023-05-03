package com.ada.framework.webmvc.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/5/3 20:15
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyHandlerMapping {
	private Object controller;
	private Method method;
	private Pattern pattern;

	public MyHandlerMapping(Pattern pattern, Object controller, Method method) {
		this.controller = controller;
		this.method = method;
		this.pattern = pattern;
	}

	public Object getController() {
		return controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
}
