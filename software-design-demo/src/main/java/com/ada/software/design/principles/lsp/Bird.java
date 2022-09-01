package com.ada.software.design.principles.lsp;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 鸟
 * <p/>
 * <b>Creation Time:</b> 2022/9/1 16:53
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class Bird implements IEatAnimal, IFlyAnimal {
	@Override
	public void eat() {
		System.out.println("鸟会吃");
	}

	@Override
	public void fly() {
		System.out.println("鸟会飞");
	}
}
