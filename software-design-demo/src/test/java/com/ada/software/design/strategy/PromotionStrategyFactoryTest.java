package com.ada.software.design.strategy;

import com.ada.software.design.strategy.activity.PromotionActivity;
import com.ada.software.design.strategy.activity.PromotionStrategyFactory;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 工厂模式+单例测试类
 * <p/>
 *
 * @Date: 2022/10/8 23:01
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class PromotionStrategyFactoryTest {
	public static void main(String[] args) {
		String promotionKey = "GROUPBUY";
		PromotionActivity promotionActivity = new
				PromotionActivity(PromotionStrategyFactory.getPromotionStrategy(promotionKey));
		promotionActivity.excute();
	}
}
