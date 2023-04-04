package com.ada.spring.anno.configuration;

import org.springframework.context.annotation.Configuration;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 带有@Configuration的类：
 *
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 17:42
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
@Configuration
public class ConfigurationClassImport {
	public void printName() {
		System.out.println("类名 ：" + Thread.currentThread().getStackTrace()[1].getClassName());
	}
}
