package com.ada.software.design.decorator.batter;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 19:56
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class BaseBattercake extends  Battercake{
	@Override
	protected String getMsg() {
		return "煎饼";
	}

	@Override
	protected int getPrice() {
		return 5;
	}
}
