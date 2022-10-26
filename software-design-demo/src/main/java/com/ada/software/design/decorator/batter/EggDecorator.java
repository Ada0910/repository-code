package com.ada.software.design.decorator.batter;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 19:58
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class EggDecorator extends BattercakeDecotator {
	public EggDecorator(Battercake battercake) {
		super(battercake);
	}

	@Override
	protected void doSomething() {

	}

	protected String getMsg() {
		return super.getMsg() + "+1 个鸡蛋";
	}

	@Override
	protected int getPrice() {
		return super.getPrice() + 1;
	}
}
