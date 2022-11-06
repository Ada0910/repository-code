package com.ada.jvm.simple;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 *
 * @Date: 2022/11/3 22:22
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Person {
	private String name;

	private int age;

	private static String address;

	private final static String hobby = "Programming";


	public void say() {
		System.out.println("person say...");
	}


	public static int calc(int op1, int op2) {
		op1 = 3;
		int result = op1 + op2;
		return result;
	}
}
