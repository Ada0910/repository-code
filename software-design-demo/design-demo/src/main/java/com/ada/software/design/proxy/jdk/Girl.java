package com.ada.software.design.proxy.jdk;

import com.ada.software.design.proxy.Person;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 15:18
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class Girl implements Person {

	//找对象的方法
	@Override
	public void findLove() {
		System.out.println("高富帅");
		System.out.println("身高180cm");
		System.out.println("有6块腹肌");
	}
}
