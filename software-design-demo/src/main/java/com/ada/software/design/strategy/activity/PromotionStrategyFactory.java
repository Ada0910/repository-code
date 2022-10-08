package com.ada.software.design.strategy.activity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 利用单例和工厂模式进行改造
 * <p/>
 *
 * @Date: 2022/10/8 22:55
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class PromotionStrategyFactory {

	private static Map<String, IPromotionStrategy> map = new HashMap<>();

	// 静态化加载
	static {
		map.put(PromotionKey.COUPON, new CouponStrategy());
		map.put(PromotionKey.CASHBACK, new CashbackStrategy());
		map.put(PromotionKey.GROUPBUY, new GroupbuyStrategy());
	}

	private static final IPromotionStrategy NON_PROMOTION = new EmptyStrategy();

	// 构造方法
	private PromotionStrategyFactory() {
	}

	public static IPromotionStrategy getPromotionStrategy(String promotionKey) {
		IPromotionStrategy promotionStrategy = map.get(promotionKey);
		return promotionStrategy == null ? NON_PROMOTION : promotionStrategy;
	}


	// 内部类
	private interface PromotionKey {
		String COUPON = "COUPON";
		String CASHBACK = "CASHBACK";
		String GROUPBUY = "GROUPBUY";
	}
}
