package com.ada.basis.inner.statics;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 静态内部类：
 *
 * (1)静态内部类四种类中唯一一个不包含对外部类对象的引用的内部类
 * (2)静态内部类可以定义static成员
 * (3)静态内部类访问外部类任何静态数据成员与方法
 *
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 15:02
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since 1.0.0.1
 */
public class Outer {
	public final String name = "外部类！";

	public static Integer age = 18;

	public String getName() {
		System.out.println( Inner.getMethod() + Inner.name);
		return Inner.getMethod() + Inner.name;
	}


	//静态内部类
	public static class Inner {
		private static String name = "静态内部类";

		public Integer age = 32;

		public static String getMethod() {
			return "调用静态内部类的方法";
		}

	}

	public static void main(String[] args) {
		Outer outer = new Outer();
		outer.getName();
	}
}
