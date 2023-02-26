package com.ada.starter.config;

import com.ada.starter.split.ISplitService;
import com.ada.starter.split.SplitServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 关键的配置类：创建的字符串分割功能以配置组件的形式加入IOC容器中，方便被扫描
 *
 * 注解解释：
 * ConditionalOnMissingBean注解：它是修饰bean的一个注解，主要实现的是，当你的bean被注册之后，如果而注册相同类型的bean，就不会成功，它会保证你的bean只有一个，即你的实例只有一个
 * ConditionalOnClass注解：当项目中存在某个类时才会使标有该注解的类或方法生效
 * ConfigurationProperties注解：主要用来把properties配置文件转化为bean来使用的
 * EnableConfigurationProperties注解： @ConfigurationProperties注解生效
 * <p/>
 *
 * @Date: 2023/2/26 0:45
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Configuration
@EnableConfigurationProperties()
@ComponentScan(basePackages = {"com.ada.starter"})
public class SplitAutoConfig {

	@Bean
	@ConditionalOnMissingBean
	ISplitService createService() {
		return new SplitServiceImpl();
	}

}
