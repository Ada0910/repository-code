package com.ada.software.design.singleton.lazy;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 单例模式之懒汉式（静态内部类）
 * 只有在外部调用的时候，才会初始化对象
 *
 * 性能最优的写法（牛逼）
 * <p/>
 *
 * @Date: 2022/9/17 19:13
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class LazyInnerClassSingleton {

	/**
	 * 构造方法私有化
	 */
	private LazyInnerClassSingleton() {
		if (LazyHolder.LAZY_INNERCLASS_SINGLETON != null) {
			throw new RuntimeException("不允许创建多个实例");
		}
	}

	/**
	 * 全局调用方法（巧妙的利用了内部类的特性）
	 */
	public static LazyInnerClassSingleton getInstance() {
		return LazyHolder.LAZY_INNERCLASS_SINGLETON;
	}

	/**
	 * 静态内部类(内容类的加载会比外部类的快)
	 */

	private static class LazyHolder {
		private static final LazyInnerClassSingleton LAZY_INNERCLASS_SINGLETON = new LazyInnerClassSingleton();
	}
}
