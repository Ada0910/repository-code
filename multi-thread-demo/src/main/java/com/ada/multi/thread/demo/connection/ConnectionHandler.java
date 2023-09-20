package com.ada.multi.thread.demo.connection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/9/20 23:11
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ConnectionHandler implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getName().equals("commit")) {
			TimeUnit.MILLISECONDS.sleep(100);
		}
		return null;
	}
}
