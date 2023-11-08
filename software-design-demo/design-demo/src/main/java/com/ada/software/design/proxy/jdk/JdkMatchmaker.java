package com.ada.software.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 动态代理之JDK代理
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 14:55
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class JdkMatchmaker implements InvocationHandler {

	//被代理的对象，把引用给保存下来
	private Object target;

	public Object getInstance(Object target) throws Exception {
		this.target = target;
		Class<?> clazz = target.getClass();
		//JDK代理
		return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
	}

	/**
	 *  通过反射进行代理调用
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object obj = method.invoke(this.target, args);
		after();
		return obj;
	}

	/**
	 *  调用之后执行的增强代码
	 */
	private void after() {
		System.out.println("如果合适的话，就准备办事");
	}

	/**
	 *  调用之前执行的增强代码
	 */
	private void before() {
		System.out.println("我是媒婆：我要给你找对象，现在已经拿到你的需求");
		System.out.println("开始物色");
	}
}
