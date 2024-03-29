package com.ada.boot.boot.anno3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/6 17:35
 * @Author xwn
 * @Version 1.0.0.1
 *
 */

@SpringBootApplication
@EnableDefineService
public class Test {
	public static void main(String[] args) {
		ConfigurableApplicationContext ca= SpringApplication.run(Test.class,args);

		System.out.println(ca.getBean(CacheService.class));
		System.out.println(ca.getBean(LoggerService.class));

	}
}
