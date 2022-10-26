package com.ada.software.design.decorator.simple;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 19:49
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class BattercakeWithEggAndSausage extends BattercakeWithEgg {
	@Override
	protected String getMsg() {
		return super.getMsg() + "+1 根香肠";
	}

	@Override
//加一个香肠加 2 块钱
	public int getPrice() {
		return super.getPrice() + 2;
	}
}
