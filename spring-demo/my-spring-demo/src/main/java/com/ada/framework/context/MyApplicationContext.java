package com.ada.framework.context;

import com.ada.framework.aop.MyAopProxy;
import com.ada.framework.aop.MyCglibAopProxy;
import com.ada.framework.aop.MyJdkDynamicAopProxy;
import com.ada.framework.aop.config.MyAopConfig;
import com.ada.framework.aop.support.MyAdvisedSupport;
import com.ada.framework.beans.MyBeanFactory;
import com.ada.framework.beans.MyBeanWrapper;
import com.ada.framework.beans.config.MyBeanDefinition;
import com.ada.framework.beans.config.MyBeanPostProcessor;
import com.ada.framework.beans.support.MyBeanDefinitionReader;
import com.ada.framework.beans.support.MyDefaultListableBeanFactory;
import com.ada.spring.anno.MyAutowired;
import com.ada.spring.anno.MyController;
import com.ada.spring.anno.MyService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

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

	private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

	private Map<String, MyBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();


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
		try {
			//	只处理非延时加载的情况
			for (Map.Entry<String, MyBeanDefinition> beanDefinitionEntry : beanDefinitionMap.entrySet()) {
				String beanName = (String) getBean(beanDefinitionEntry.getKey());

				if (!beanDefinitionEntry.getValue().getLazyInit()) {
					getBean(beanName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * 提供一个获取全局bean的方法
	 */
	@Override
	public Object getBean(String beanName) throws Exception {

		MyBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);

		//1.初始化
		Object instance = instantiateBean(beanName, beanDefinition);

		//前置通知
		MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
		beanPostProcessor.postProcessBeforeInitialization(instance, beanName);

		//3.把这个对象封装到beanWrapper中
		MyBeanWrapper beanWrapper = new MyBeanWrapper(instance);

		//创建一个代理策略,看事用CGLiB还是用JDK

		//2.拿到BeanWrapper之后，把BeanWrapper保存到IOC容器中
		this.factoryBeanInstanceCache.put(beanName, beanWrapper);


		//后置通知
		beanPostProcessor.postProcessAfterInitialization(instance, beanName);

		//	3.注入
		populateBean(beanName, new MyBeanDefinition(), beanWrapper);

		return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
	}


	/**
	 * 初始化
	 */
	private Object instantiateBean(String beanName, MyBeanDefinition beanDefinition) {
		//1.拿到实例化的对象的类名
		String className = beanDefinition.getBeanClassName();

		//2.反射实例化
		Object instance = null;
		try {
			if (this.singletonObjects.containsKey(className)) {
				instance = this.singletonObjects.get(className);
			} else {
				Class<?> clazz = Class.forName(className);
				instance = clazz.newInstance();

				//AOP
				MyAdvisedSupport config = instantionAopConfig(beanDefinition);
				config.setTargetClass(clazz);
				config.setTarget(instance);

				//符合PointCut的规则的话，闯将代理对象
				if(config.pointCutMatch()) {
					instance = createProxy(config).getProxy();
				}

				this.singletonObjects.put(className, instance);
				this.singletonObjects.put(beanDefinition.getFactoryBeanName(), instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		//4.把beanWrapper存到IOC容器中
		return instance;
	}

	/**
	 * 注入
	 */
	private void populateBean(String beanName, MyBeanDefinition beanDefinition, MyBeanWrapper beanWrapper) {
		Object instance = beanWrapper.getWrappedInstance();

		//判断只有加了注解的类，才执行依赖注入
		Class<?> clazz = beanWrapper.getWrappedClass();

		if (!(clazz.isAnnotationPresent(MyController.class) || clazz.isAnnotationPresent(MyService.class))) {
			return;
		}

		//获得所有fields
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(MyAutowired.class)) {
				continue;
			}
			MyAutowired autowired = field.getAnnotation(MyAutowired.class);
			String value = autowired.value().trim();

			if ("".equals(value)) {
				value = field.getType().getName();
			}

			field.setAccessible(true);

			try {
				field.set(instance, this.factoryBeanInstanceCache.get(value).getWrappedClass());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * 获取所有BeanDefinition的名字
	 */
	public String[] getBeanDefinitionNames() {
		return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
	}

	/**
	 * 获取到beanDefinition有多少个对象
	 */
	public int getBeanDefinitionCount() {
		return this.beanDefinitionMap.size();
	}

	public Properties getConfig(){
		return this.reader.getConfig();
	}


	private MyAdvisedSupport instantionAopConfig(MyBeanDefinition BeanDefinition) {
		MyAopConfig config = new MyAopConfig();
		config.setPointCut(this.reader.getConfig().getProperty("pointCut"));
		config.setAspectClass(this.reader.getConfig().getProperty("aspectClass"));
		config.setAspectBefore(this.reader.getConfig().getProperty("aspectBefore"));
		config.setAspectAfter(this.reader.getConfig().getProperty("aspectAfter"));
		config.setAspectAfterThrow(this.reader.getConfig().getProperty("aspectAfterThrow"));
		config.setAspectAfterThrowingName(this.reader.getConfig().getProperty("aspectAfterThrowingName"));
		return new MyAdvisedSupport(config);
	}

	private MyAopProxy createProxy(MyAdvisedSupport config) {

		Class targetClass = config.getTargetClass();
		if(targetClass.getInterfaces().length > 0){
			return new MyJdkDynamicAopProxy(config);
		}
		return new MyCglibAopProxy(config);
	}
}
