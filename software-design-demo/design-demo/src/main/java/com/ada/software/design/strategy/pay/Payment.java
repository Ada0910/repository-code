package com.ada.software.design.strategy.pay;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 支付渠道
 * <p/>
 *
 * @Date: 2022/10/8 23:04
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public abstract class Payment {
	//支付类型
	public abstract String getName();

	//查询余额
	protected abstract double queryBalance(String uid);

	//扣款支付
	public MsgResult pay(String uid, double amount) {
		if (queryBalance(uid) < amount) {
			return new MsgResult(500, "支付失败", "余额不足");
		}
		return new MsgResult(200, "支付成功", "支付金额：" + amount);
	}

}