package com.ada.software.design.strategy.activity;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 促销活动
 * <p/>
 *
 * @Date: 2022/10/8 22:50
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class PromotionActivity {

	private IPromotionStrategy promotionStrategy;

	public PromotionActivity(IPromotionStrategy promotionStrategy) {
		this.promotionStrategy = promotionStrategy;
	}

	public void excute() {
		promotionStrategy.doPromotion();
	}
}
