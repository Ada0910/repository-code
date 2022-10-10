package com.ada.api.dto;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 用户
 * <p/>
 *
 * @Date: 2022/10/10 23:26
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class User {

	private String name;

	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
	}
}
