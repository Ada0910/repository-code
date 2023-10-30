package com.ada.basis.inner.local;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 局部内部类：
 *
 * (1)局部内部类不能有访问权限修饰符,即public这种都不可以，也不可以用static修改
 * (2)局部内部类不能定义static成员
 * (3)如同使用this一样，当成员名或方法名发生覆盖时，可以使用外部类的名字加.this指定访问外部类成员。如：Outer.this.name
 *
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 14:43
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class Outer {

	public String name = "外部类";

	private int age = 18;

	private int weight = 70;

	public void getLocalClass() {
		//局部内部类，前面不能加权限修饰符
		class Inner {
			public String name = "局部内部类";

			private int age = 17;
			
			
			//可以用这种格式获取外部类的信息
			public String getOuterName(){
				System.out.println(Outer.this.name);
				return Outer.this.name;
			}
		}
		new Inner().getOuterName();
	}


	public static void main(String[] args) {
		Outer outer = new Outer();
		outer.getLocalClass();
	}
}
