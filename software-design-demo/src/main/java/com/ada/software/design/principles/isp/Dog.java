package com.ada.software.design.principles.isp;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 狗
 * <p/>
 * <b>Creation Time:</b> 2022/9/1 16:52
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class Dog implements IEatAnimal, ISwimAnimal {
	@Override
	public void eat() {
		System.out.println("狗会吃");
	}

	@Override
	public void swim() {
		System.out.println("狗会游泳");
	}
}
