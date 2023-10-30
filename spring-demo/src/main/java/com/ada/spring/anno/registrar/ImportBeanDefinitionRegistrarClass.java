package com.ada.spring.anno.registrar;

import org.springframework.context.annotation.Configuration;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 实现ImportBeanDefinitionRegistrar接口 
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 18:04
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */

public class ImportBeanDefinitionRegistrarClass {
	public void printName() {
		System.out.println("类名 ：" + Thread.currentThread().getStackTrace()[1].getClassName());
	}
}
