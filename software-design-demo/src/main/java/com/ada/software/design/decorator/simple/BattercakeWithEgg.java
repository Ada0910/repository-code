package com.ada.software.design.decorator.simple;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 19:48
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class BattercakeWithEgg extends Battercake {
	protected String getMsg() {
		return super.getMsg() + "+1 个鸡蛋";
	}

	@Override
//加一个鸡蛋加 1 块钱
	public int getPrice() {
		return super.getPrice() + 1;
	}
}
