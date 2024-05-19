package com.ada.format;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FormatSpringBootStarterApplication {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FormatSpringBootStarterApplication.class, args);
		// 打印出所加载的类
		String[] beanNames = context.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			Object bean = context.getBean(beanName);
			System.out.println("===========" + bean.getClass().getName());
		}


	}
}
