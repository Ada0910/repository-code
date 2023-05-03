package com.ada.framework.beans.config;

import lombok.Data;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/5/1 12:14
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Data
public class MyBeanDefinition {

	private String beanClassName;

	private Boolean lazyInit = false;

	private String factoryBeanName;

	private boolean isSingleton = true;

}
