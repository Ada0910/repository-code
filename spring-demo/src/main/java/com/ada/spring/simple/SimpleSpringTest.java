package com.ada.spring.simple;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/9/6 23:56
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SimpleSpringTest {
	public static void main(String[] args) {
		ClassPathResource resource = new ClassPathResource("beans.xml");
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(resource);
	}
}
