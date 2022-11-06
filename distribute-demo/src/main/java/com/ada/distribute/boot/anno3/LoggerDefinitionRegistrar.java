package com.ada.distribute.boot.anno3;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/6 17:56
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class LoggerDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		Class beanClass=LoggerService.class;
		RootBeanDefinition beanDefinition=new RootBeanDefinition(beanClass);
		String beanName= StringUtils.uncapitalize(beanClass.getSimpleName());
		registry.registerBeanDefinition(beanName,beanDefinition);

	}
}
