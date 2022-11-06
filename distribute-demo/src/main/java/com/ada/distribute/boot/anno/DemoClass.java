package com.ada.distribute.boot.anno;

import org.springframework.stereotype.Service;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/6 16:38
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Service
public class DemoClass {

	/**
	 * Spring 3.0 之前，如果我们需要把这个类交给spring托管，则需要用下面的方式
	 * <bean name="" class="com.ada.distribute.boot.anno.DemoClass"/>
	 *
	 */
	public void say() {
		System.out.println("Say: Hello ada");
	}
}