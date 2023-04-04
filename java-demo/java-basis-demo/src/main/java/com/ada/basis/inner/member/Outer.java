package com.ada.basis.inner.member;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 成员内部类:
 *
 * (1)成员内部类可以用public,default,protected,private等修饰，不可以用static 修饰
 * (2)成员内部类可以访问外部类的成员变量，即使是private成员也是可以的
 * (3)成员内部类是依赖于外部类存在的，所以外部类必须是先要是实例化才能访问内部类
 * (4)如同使用this一样，当成员名或方法名发生覆盖时，可以使用外部类的名字加.this指定访问外部类成员。如：Outer.this.name
 * (4)
 *
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 11:34
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class Outer {

	public String name = "ada";

	private int age = 18;

	private int weight = 70;

	public String getName() {
		return name;
	}

	private Integer getAge() {
		return age;
	}

	private class Inner {
		//public static  int age2 = 30; //1.static 修饰会报错
		public int age = 30;
		private Double height = 1.70;
		public String name = "阿哒";
		/* 
		 * 内部类不能定义static对象和方法
		 * 但是可以定义static final变量，这并不冲突，因为所定义的final字段必须是编译时确定的，而且在编译类时会将对应的变量替换为具体的值，所以在JVM看来，并没有访问内部类 
		 */
		final  static  String introduces = "可以定义static final变量，这并不冲突，因为所定义的final字段必须是编译时确定的，而且在编译类时会将对应的变量替换为具体的值，所以在JVM看来，并没有访问内部类";

		//常规覆盖
		public String getName() {
			return name;
		}


		private Integer getAge() {
			return age;
		}

		private Double getHeight() {
			return height;
		}

		//内部类可以访问外部类的成员变量，即使是private成员也是可以的
		private Integer getWeight() {
			return weight;
		}
		
		//4.可以用这种格式获取外部类的信息
		public String gerParentName() {
			return Outer.this.name;
		}

	}


	/**
	 *  测试方法
	 */
	public static void main(String[] args) {
		//3.需要先初始化外部类，内部类才能访问
		Outer outer = new Outer();
		System.out.println(outer.getAge());
		System.out.println(outer.getName());

		//内部类实例化
		Outer.Inner inner = outer.new Inner();
		System.out.println(inner.getAge());
		System.out.println(inner.getHeight());
		System.out.println(inner.getName());

		//内部类访问外部对象
		System.out.println(inner.getWeight());
		System.out.println(inner.gerParentName());

	}
}
