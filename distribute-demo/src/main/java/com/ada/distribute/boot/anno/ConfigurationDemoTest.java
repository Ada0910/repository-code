package com.ada.distribute.boot.anno;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/6 16:54
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@ComponentScan
public class ConfigurationDemoTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationDemo.class);
		/*
		 * 主要是用于测试@Configuration
		 *  DI模式
		 */
		// DemoClass bean = applicationContext.getBean(DemoClass.class);
		// bean.say();

		/*
		 * @ComponentScan注解(扫描如@Service这些)
		 */
		String[] names = applicationContext.getBeanDefinitionNames();
		for(int i=0;i<names.length;i++){
			System.out.println(names[i]);
		}


	}
}
