package com.ada.software.design.proxy.jdk;

import java.lang.reflect.Method;

/**
 *
 * <b><code></code></b>
 * <p/>
 * JDK动态代理测试类
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 15:05
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class JDKProxyTest {
	public static void main(String[] args) throws Exception {
		Object obj = new JdkMatchmaker().getInstance(new Girl());
		Method m = obj.getClass().getMethod("findLove", null);
		m.invoke(obj);
	}
}
