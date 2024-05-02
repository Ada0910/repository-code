package com.ada.simple.base.threadlocal.simple;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/23 23:08
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadLocalSimpleDemo {

	public static ThreadLocal threadLocal = new ThreadLocal();

	public static void main(String[] args) {
		if (threadLocal.get() == null) {
			System.out.println("ThreadLocal中没有值！");
			threadLocal.set("我的值");
		}
		System.out.println(threadLocal.get());
		System.out.println(threadLocal.get());
	}
}
