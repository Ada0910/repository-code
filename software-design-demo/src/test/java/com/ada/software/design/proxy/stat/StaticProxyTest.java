package com.ada.software.design.proxy.stat;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 找对象测试类(静态代理测试类)
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 11:15
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class StaticProxyTest {
	public static void main(String[] args) {
		Father father = new Father(new Son());
		father.findLove();
	}
}
