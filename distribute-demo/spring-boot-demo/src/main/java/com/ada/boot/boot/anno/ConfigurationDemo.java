package com.ada.boot.boot.anno;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/6 16:49
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Configuration
public class ConfigurationDemo {

	/**
	 *  Spring 3.0 之后，支持配置化，则用一个@Bean和@Configuration就能够将该类托管给Spring
	 */
	@Bean
	public DemoClass getDemoClass() {
		return new DemoClass();
	}
}
