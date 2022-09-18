package com.ada.software.design.prototype.simple;

import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 创建具体需要克隆的对象 ConcretePrototype
 * <p/>
 *
 * @Date: 2022/9/18 15:50
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ConcretePrototypeA implements Prototype {
	private int age;
	private String name;
	private List hobbies;

	@Override
	public Prototype clone() {
		ConcretePrototypeA concretePrototype = new ConcretePrototypeA();
		concretePrototype.setAge(this.age);
		concretePrototype.setName(this.name);
		concretePrototype.setHobbies(this.hobbies);
		return concretePrototype;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getHobbies() {
		return hobbies;
	}

	public void setHobbies(List hobbies) {
		this.hobbies = hobbies;
	}
}
