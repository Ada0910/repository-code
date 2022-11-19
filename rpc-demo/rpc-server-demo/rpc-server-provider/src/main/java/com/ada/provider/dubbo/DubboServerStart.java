package com.ada.provider.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/19 14:34
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class DubboServerStart {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/application.xml"});
		classPathXmlApplicationContext.start();
		System.in.read();
	}
}
