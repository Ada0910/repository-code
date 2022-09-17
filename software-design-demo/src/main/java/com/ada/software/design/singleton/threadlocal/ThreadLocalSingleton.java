package com.ada.software.design.singleton.threadlocal;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 单例模式之ThreadLocal
 *
 * <p/>
 *
 * @Date: 2022/9/17 23:40
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadLocalSingleton {


	private static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance = new ThreadLocal<ThreadLocalSingleton>() {

		@Override
		protected ThreadLocalSingleton initialValue() {
			return new ThreadLocalSingleton();
		}
	};

	/**
	 * 构造方法私有化
	 */
	public ThreadLocalSingleton() {
	}

	/**
	 * 公共访问方法
	 */
	public static ThreadLocalSingleton getInstance() {
		return threadLocalInstance.get();
	}


}
