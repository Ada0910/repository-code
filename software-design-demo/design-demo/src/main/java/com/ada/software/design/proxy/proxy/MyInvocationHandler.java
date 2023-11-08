package com.ada.software.design.proxy.proxy;

import java.lang.reflect.Method;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/28 11:05
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public interface MyInvocationHandler {
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable;
}
