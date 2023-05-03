package com.ada.framework.beans;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 单例工厂的顶层设计
 * <p/>
 *
 * @Date: 2023/5/1 11:41
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public interface MyBeanFactory {

	/**
	 * 根据beanName从IOC容器中获取一个实例Bean
	 */
	Object getBean(String beanName) throws Exception;
}
