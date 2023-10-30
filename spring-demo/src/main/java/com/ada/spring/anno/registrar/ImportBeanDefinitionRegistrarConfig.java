package com.ada.spring.anno.registrar;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 实现SelfImportBeanDefinitionRegistrar 接口
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 18:05
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class ImportBeanDefinitionRegistrarConfig implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		RootBeanDefinition root = new RootBeanDefinition(ImportBeanDefinitionRegistrarClass.class);
		registry.registerBeanDefinition("ImportBeanDefinitionRegistrarClass", root);	}
}
