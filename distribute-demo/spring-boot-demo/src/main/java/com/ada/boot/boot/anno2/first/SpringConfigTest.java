package com.ada.boot.boot.anno2.first;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * <b><code></code></b>
 * <p/>
 *  测试类
 * <p/>
 *
 * @Date: 2022/11/6 16:50
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SpringConfigTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new
				AnnotationConfigApplicationContext(SpringConfig.class);

		String[] defNames = applicationContext.getBeanDefinitionNames();
		for (int i = 0; i < defNames.length; i++) {
			System.out.println(defNames[i]);
		}
	}
}
