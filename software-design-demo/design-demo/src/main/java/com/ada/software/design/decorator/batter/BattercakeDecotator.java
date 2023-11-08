package com.ada.software.design.decorator.batter;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 19:57
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public abstract class BattercakeDecotator extends Battercake {
	//静态代理，委派
	private Battercake battercake;

	public BattercakeDecotator(Battercake battercake) {
		this.battercake = battercake;
	}

	protected abstract void doSomething();

	@Override
	protected String getMsg() {
		return battercake.getMsg();
	}

	@Override
	protected int getPrice() {
		return battercake.getPrice();
	}
}
