package com.ada.software.design.singleton.register;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 单例模式之枚举式（枚举登记）
 * 就是将每一个实例都登记到某一个地方，使用唯一的标识获取实例
 *
 * <p/>
 *
 * @Date: 2022/9/17 21:43
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public enum EnumSingleton {
	INSTANCE;

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 提供一个全局的调用方法
	 */
	public static EnumSingleton getInstance() {
		return INSTANCE;
	}
}
