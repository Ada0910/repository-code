package com.ada.software.design.proxy.db;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 订单逻辑实现层
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 11:40
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class OrderServiceImpl implements IOrderService {
	private OrderDao orderDao;

	public OrderServiceImpl() {
		//如果使用Spring应该是自动注入的
		//我们为了使用方便，在构造方法中将orderDao直接初始化了
		orderDao = new OrderDao();
	}

	//创建订单
	public int createOrder(Order order) {
		System.out.println("OrderService调用orderDao创建订单");
		return orderDao.insert(order);
	}
}
