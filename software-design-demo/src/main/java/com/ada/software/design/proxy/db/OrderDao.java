package com.ada.software.design.proxy.db;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 订单数据操作层
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 11:39
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class OrderDao {
	//创建订单
	public int insert(Order order) {
		System.out.println("OrderDao创建Order成功!");
		return 1;
	}
}
