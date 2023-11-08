package com.ada.boot.boot.anno2.first;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ada.boot.boot.anno2.second.OtherConfig;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 *
 * @Date: 2022/11/6 16:50
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Import(OtherConfig.class)
@Configuration
public class SpringConfig {

	/**
	 * 如果没有加@Import(OtherConfig.class)，test类是扫描不了second的那个目录下的bean的
	 */
	@Bean
	public DefaultBean defaultBean() {
		return new DefaultBean();
	}
}
