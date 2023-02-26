package com.ada.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 配置类：
 *
 * ConfigurationProperties注解：跟@value差不多，只是可以批量获取属性的值
 *
 * <p/>
 *
 * @Date: 2023/2/26 17:49
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@ConfigurationProperties(prefix = "ada")
@Component
public class AuthorInfoProperties {

	String name;

	Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
