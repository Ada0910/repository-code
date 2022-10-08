package com.ada.software.design.strategy.activity;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 优惠券抵扣策略 CouponStrategy 类
 * <p/>
 *
 * @Date: 2022/10/8 22:45
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class CouponStrategy implements IPromotionStrategy {
	@Override
	public void doPromotion() {
		System.out.println("领取优惠券,课程的价格直接减优惠券面值抵扣");
	}
}
