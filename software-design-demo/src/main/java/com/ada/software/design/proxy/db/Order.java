package com.ada.software.design.proxy.db;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 订单实体类
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 11:37
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class Order {
	private Object orderInfo;
	//订单创建时间进行按年分库
	private Long createTime;
	private String id;

	public Object getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(Object orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
