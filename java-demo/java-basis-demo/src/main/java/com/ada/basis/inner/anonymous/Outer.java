package com.ada.basis.inner.anonymous;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 匿名内部类：
 *
 * (1)匿名内部类使用单独的块表示初始化块{}
 * (2)匿名内部类想要使用方法或域中的变量，该变量必须是final修饰的，JDK1.8之后effectively final也可以
 * (3)匿名内部类默认包含了外部类对象的引用
 * (4)匿名内部类表示继承所依赖的类
 * (5)匿名内部类使用到外部类方法中的局部变量时需要是final类型的
 *
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 15:01
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since 1.0.0.1
 */
public class Outer {

	public final String name = "外部类！";


	//匿名内部类
	private Runnable runnable = new Runnable() {

		public void run() {}
	};

	public static void main(String[] args) {
		Outer outer = new Outer();

	}
}
