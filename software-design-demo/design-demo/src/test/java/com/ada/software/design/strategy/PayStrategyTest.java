package com.ada.software.design.strategy;

import com.ada.software.design.strategy.pay.Order;
import com.ada.software.design.strategy.pay.PayStrategy;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/8 23:18
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class PayStrategyTest {
	public static void main(String[] args) {
		Order order = new  Order("1","20180311001000009",324.45);
		System.out.println(order.pay(PayStrategy.ALI_PAY));
	}
}

