package com.ada.software.design.proxy.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ada.software.design.proxy.db.dynamic.OrderServiceDynamicProxy;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 14:31
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class DbRouteProxyTest {
	public static void main(String[] args) throws Exception {
		Order order = new Order();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = sdf.parse("2017/02/01");
		order.setCreateTime(date.getTime());
		//静态代理类
		//IOrderService orderService = (IOrderService) new OrderServiceStaticProxy(new OrderServiceImpl());
		//orderService.createOrder(order);

		//动态代理
		IOrderService orderService = (IOrderService) new OrderServiceDynamicProxy().getInstance(new OrderServiceImpl());
		orderService.createOrder(order);
	}
}
