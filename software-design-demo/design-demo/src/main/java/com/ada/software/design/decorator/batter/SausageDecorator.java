package com.ada.software.design.decorator.batter;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 20:03
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SausageDecorator extends BattercakeDecotator {
	public SausageDecorator(Battercake battercake) {
		super(battercake);
	}

	protected void doSomething() {
	}

	@Override
	protected String getMsg() {
		return super.getMsg() + "+1 根香肠";
	}

	@Override
	protected int getPrice() {
		return super.getPrice() + 2;
	}
}
