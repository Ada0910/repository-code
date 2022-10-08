package com.ada.software.design.strategy.pay;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 微信支付
 * <p/>
 *
 * @Date: 2022/10/8 23:08
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class WechatPay extends  Payment{
	@Override
	public String getName() {
		return "微信支付";
	}

	@Override
	protected double queryBalance(String uid) {
		return 256;
	}
}
