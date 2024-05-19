package com.ada.boot.starter;


import com.ada.format.starter.FormatTemplate;
import com.ada.format.starter.config.Properties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *  format-spring-boot-starter 测试类
 *
 *  1.引包
 *  2.配置文件（application.properties）  ada.format.info.age等
 *  3.使用
 * <p/>
 *
 * @Date: 2024/5/19 16:09
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@SpringBootTest
public class FormatTemplateTest {

	Properties properties = new Properties();
	@Autowired
	private ApplicationContext context;

	/**
	 * 打印出Spring Boot加载的类有哪些
	 */
	@Test
	public void testPrintLoadedClasses() {
		String[] beanNames = context.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			Object bean = context.getBean(beanName);
			System.out.println("==========="+bean.getClass().getName());
		}
	}

	@Autowired
	public FormatTemplate template;

	/**
	 * 测试format-spring-boot-starter
	 */
	@Test
	public void format() {
		String s = template.doFormat(properties);
		System.out.println(s);
	}
}
