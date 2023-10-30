package com.ada.software.design.proxy.proxy;

import java.lang.reflect.Method;

import com.ada.software.design.proxy.Person;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/28 11:06
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class Matchmaker implements MyInvocationHandler {
	//被代理类
	private Object target;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object obj = method.invoke(this.target, args);
		after();
		return obj;
	}

	public Object getInstance(Object obj) {
		this.target = obj;
		Class<?> clazz = target.getClass();
		return MyProxy.newProxyInstance(new MyClassLoader(), clazz.getInterfaces(), this);
	}

	private void before() {
		System.out.println("我是媒婆，我要给你找对象，现在已经确认你的需求");
		System.out.println("开始物色");
	}

	private void after() {
		System.out.println("OK的话，准备办事");
	}
}
