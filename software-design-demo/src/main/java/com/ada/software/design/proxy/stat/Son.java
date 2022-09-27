package com.ada.software.design.proxy.stat;

import com.ada.software.design.proxy.Person;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 儿子要找对象（被代理对象）
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 11:13
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class Son implements Person {
	@Override
	public void findLove() {
		System.out.println("Son 要求 温柔贤惠");
	}
}
