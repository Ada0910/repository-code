package com.ada.software.design.proxy.db;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 订单逻辑层 接口
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 11:39
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public interface IOrderService {
	//创建订单
	int createOrder(Order order);
}
