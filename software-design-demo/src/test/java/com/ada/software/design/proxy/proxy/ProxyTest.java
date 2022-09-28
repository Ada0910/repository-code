package com.ada.software.design.proxy.proxy;

import com.ada.software.design.proxy.Person;
import com.ada.software.design.proxy.jdk.Girl;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 手写代理测试类
 * <p/>
 * <b>Creation Time:</b> 2022/9/28 10:54
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class ProxyTest {
	public static void main(String[] args) {
		Person obj = (Person) new Matchmaker().getInstance(new Girl());
		System.out.println(obj.getClass());
		obj.findLove();
	}
}
