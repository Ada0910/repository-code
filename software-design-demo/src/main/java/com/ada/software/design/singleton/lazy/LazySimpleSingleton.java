package com.ada.software.design.singleton.lazy;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 单例模式之懒汉式（简单）
 * 只有在外部调用的时候，才会初始化对象
 *
 * <p/>
 *
 * @Date: 2022/9/17 17:31
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class LazySimpleSingleton {

	// 静态的变量
	private static LazySimpleSingleton lazySimpleSingleton;

	/**
	 * 构造方法私有化
	 */
	private LazySimpleSingleton() {
	}

	/**
	 * 全局调用方法
	 */
	public static LazySimpleSingleton getInstance() {

		//判断lazySimpleSingleton是否为空，不然每次都要创建
		if (null == lazySimpleSingleton) {
			lazySimpleSingleton = new LazySimpleSingleton();
		}
		return lazySimpleSingleton;
	}
}
