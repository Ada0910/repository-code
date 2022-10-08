package com.ada.software.design.strategy.activity;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 返现促销策略 CashbackStrategy类
 * <p/>
 *
 * @Date: 2022/10/8 22:47
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class CashbackStrategy implements IPromotionStrategy {
	@Override
	public void doPromotion() {
		System.out.println("返现促销,返回的金额转到支付宝账号");
	}
}
