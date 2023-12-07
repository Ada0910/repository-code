package com.ada.software.design.decorator.simple;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 19:53
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class BattercakeTest {
	public static void main(String[] args) {
		Battercake battercake = new Battercake();
		System.out.println(battercake.getMsg() + ",总价格：" + battercake.getPrice());
		Battercake battercakeWithEgg = new BattercakeWithEgg();
		System.out.println(battercakeWithEgg.getMsg() + ",总价格：" + battercakeWithEgg.getPrice());
		Battercake battercakeWithEggAndSausage = new BattercakeWithEggAndSausage();
		System.out.println(battercakeWithEggAndSausage.getMsg() + ",总价格：" +
				battercakeWithEggAndSausage.getPrice());
	}
}
