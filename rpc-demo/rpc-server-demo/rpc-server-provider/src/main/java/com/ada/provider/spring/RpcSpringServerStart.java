package com.ada.provider.spring;

import com.ada.provider.spring.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 改造成Spring启动
 * <p/>
 *
 * @Date: 2022/10/18 18:12
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RpcSpringServerStart {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		((AnnotationConfigApplicationContext) context).start();
	}
}
