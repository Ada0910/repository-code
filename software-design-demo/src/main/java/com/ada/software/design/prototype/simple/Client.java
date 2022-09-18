package com.ada.software.design.prototype.simple;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 客户端
 * <p/>
 *
 * @Date: 2022/9/18 15:52
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Client {
	private Prototype prototype;

	public Client(Prototype prototype) {
		this.prototype = prototype;
	}

	public Prototype startClone(Prototype concretePrototype) {
		return (Prototype) concretePrototype.clone();
	}
}
