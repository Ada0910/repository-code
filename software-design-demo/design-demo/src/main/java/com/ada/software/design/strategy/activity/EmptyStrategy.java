package com.ada.software.design.strategy.activity;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 无优惠
 * <p/>
 *
 * @Date: 2022/10/8 22:49
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class EmptyStrategy implements IPromotionStrategy {
	@Override
	public void doPromotion() {
		System.out.println("无促销活动");
	}
}
