package com.ada.software.design.strategy.pay;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 银联支付
 * <p/>
 *
 * @Date: 2022/10/8 23:14
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class UnionPay extends Payment {
	@Override
	public String getName() {
		return "银联支付";
	}

	@Override
	protected double queryBalance(String uid) {
		return 120;
	}
}
