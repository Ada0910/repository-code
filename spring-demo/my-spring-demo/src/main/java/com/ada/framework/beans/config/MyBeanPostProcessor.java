package com.ada.framework.beans.config;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/5/3 18:15
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyBeanPostProcessor {
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
		return bean;
	}
}
