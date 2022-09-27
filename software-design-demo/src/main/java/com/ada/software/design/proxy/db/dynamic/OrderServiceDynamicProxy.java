package com.ada.software.design.proxy.db.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ada.software.design.proxy.db.DataSourceEntity;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 动态代理
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 15:27
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class OrderServiceDynamicProxy implements InvocationHandler {
	private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	//被代理的对象，把引用给保存下来
	private Object target;

	public Object getInstance(Object target) {
		this.target = target;
		Class<?> clazz = target.getClass();
		return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
	}

	/**
	 *  通过反射进行代理调用
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		before(args[0]);
		Object object = method.invoke(target, args);
		after();
		return object;
	}

	/**
	 *  调用之前执行的增强代码
	 */
	private void before(Object target) throws Exception {
		System.out.println("Proxy before method.");
		Long time= (Long) target.getClass().getMethod("getCreateTime").invoke(target);
		Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
		System.out.println("静态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据。");
		DataSourceEntity.set(dbRouter);
	}

	/**
	 *  调用之后执行的增强代码
	 */
	private void after() {
		System.out.println("Proxy after method.");
	}
}
