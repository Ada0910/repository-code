package com.ada.framework.context;

import com.ada.framework.beans.MyBeanFactory;
import com.ada.framework.beans.MyBeanWrapper;
import com.ada.framework.beans.config.MyBeanDefinition;
import com.ada.framework.beans.support.MyBeanDefinitionReader;
import com.ada.framework.beans.support.MyDefaultListableBeanFactory;

import java.util.List;
import java.util.Map;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 顶层的容器实现(入口类)
 * <p/>
 *
 * @Date: 2023/5/1 11:55
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyApplicationContext extends MyDefaultListableBeanFactory implements MyBeanFactory {

	private String[] configLocations;

	private MyBeanDefinitionReader reader;


	public MyApplicationContext(String... configLocations) {
		this.configLocations = configLocations;
	}


	/**
	 * ApplicationContext入口
	 */
	@Override
	protected void refresh() {
		//1.定位配置文件
		reader = new MyBeanDefinitionReader(configLocations);

		//2.加载配置文件，扫描相关的类，把它们封装成BeanDefinition
		List<MyBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

		//3.注册，把配置的信息放到容器里面（伪IOC容器）
		doRegisterBeanDefinition(beanDefinitions);

		//4.把不是延时加载的类，提前实例化
		doAutowired();
	}

	/**
	 * 注册，把配置的信息放到容器里面（伪IOC容器）
	 */
	private void doRegisterBeanDefinition(List<MyBeanDefinition> beanDefinitions) {
		for (MyBeanDefinition beanDefinition : beanDefinitions) {
			super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
		}
	}


	/**
	 *  把不是延时加载的类，提前实例化
	 */
	private void doAutowired() {
		//	只处理非延时加载的情况
		for (Map.Entry<String, MyBeanDefinition> beanDefinitionEntry : beanDefinitionMap.entrySet()) {
			String beanName = (String) getBean(beanDefinitionEntry.getKey());

			if (!beanDefinitionEntry.getValue().getLazyInit()) {
				getBean(beanName);
			}
		}

	}


	/**
	 * 提供一个获取全局bean的方法
	 */
	@Override
	public Object getBean(String beanName) {
		//1.初始化
		instantiateBean(beanName, new MyBeanDefinition());

		//	2.注入
		populateBean(beanName, new MyBeanDefinition(), new MyBeanWrapper());

		return null;
	}


	/**
	 * 初始化
	 */
	private void instantiateBean(String beanName, MyBeanDefinition beanDefinition) {
	}

	/**
	 * 注入
	 */
	private void populateBean(String beanName, MyBeanDefinition myBeanDefinition, MyBeanWrapper myBeanWrapper) {
	}


}
