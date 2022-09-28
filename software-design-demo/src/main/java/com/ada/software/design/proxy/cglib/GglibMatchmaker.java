package com.ada.software.design.proxy.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


/**
 *
 * <b><code></code></b>
 * <p/>
 * 动态代理之CGLib动态代理
 * <p/>
 * <b>Creation Time:</b> 2022/9/28 10:05
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class GglibMatchmaker implements MethodInterceptor {

	public Object getInstance(Class<?> clazz) throws Exception {
		//相当于Proxy，代理的工具类
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		before();
		Object obj = methodProxy.invoke(o, objects);
		after();
		return obj;
	}

	/**
	 * 调用之前
	 */
	private void before() {
		System.out.println("我是媒婆，我要给你找对象，现在已经确认你的需求");
		System.out.println("开始物色");
	}

	/**
	 * 调用之后
	 */
	private void after() {
		System.out.println("OK的话，准备办事");
	}
}
