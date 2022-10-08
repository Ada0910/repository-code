package com.ada.software.design.strategy.activity;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 拼团优惠策略 GroupbuyStrategy 类
 * <p/>
 *
 * @Date: 2022/10/8 22:48
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class GroupbuyStrategy implements IPromotionStrategy {
	@Override
	public void doPromotion() {
		System.out.println("拼团，满 20 人开团，全团享受团购价");
	}
}
