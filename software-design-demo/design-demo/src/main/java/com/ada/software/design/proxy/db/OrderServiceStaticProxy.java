package com.ada.software.design.proxy.db;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 静态代理类
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 14:28
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class OrderServiceStaticProxy implements IOrderService {
	private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

	private IOrderService orderService;

	public OrderServiceStaticProxy(IOrderService orderService) {
		this.orderService = orderService;
	}

	//创建订单
	@Override
	public int createOrder(Order order) {
		Long time = order.getCreateTime();
		Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
		System.out.println("静态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据");
		DataSourceEntity.set(dbRouter);

		this.orderService.createOrder(order);
		DataSourceEntity.restore();

		return 0;
	}
}

