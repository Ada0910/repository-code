package com.ada.software.design.strategy;

import com.ada.software.design.strategy.activity.CashbackStrategy;
import com.ada.software.design.strategy.activity.CouponStrategy;
import com.ada.software.design.strategy.activity.PromotionActivity;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/8 22:51
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class PromotionActivityTest {
	public static void main(String[] args) {
		PromotionActivity activity = new PromotionActivity(new CashbackStrategy());
		PromotionActivity activity2 = new PromotionActivity(new CouponStrategy());

		activity.excute();
		activity2.excute();
	}
}
