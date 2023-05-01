package com.ada.framework.beans.support;

import com.ada.framework.beans.config.MyBeanDefinition;
import com.ada.framework.context.support.MyAbstactApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/5/1 12:09
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyDefaultListableBeanFactory extends MyAbstactApplicationContext {

	protected final Map<String, MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
}
