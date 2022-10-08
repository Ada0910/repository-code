package com.ada.software.design.strategy.pay;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 京东支付
 * <p/>
 *
 * @Date: 2022/10/8 23:07
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class JDPay extends Payment {
	@Override
	public String getName() {
		return "京东白条";
	}

	@Override
	protected double queryBalance(String uid) {
		return 500;
	}
}
