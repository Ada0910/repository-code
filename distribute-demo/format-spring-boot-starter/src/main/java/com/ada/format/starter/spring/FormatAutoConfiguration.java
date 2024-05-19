package com.ada.format.starter.spring;

import com.ada.format.starter.format.IFormatProcessor;
import com.ada.format.starter.format.JsonFormatProcessor;
import com.ada.format.starter.format.StringFormatProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 *
 * <p/>
 * <b>Creation Time:</b> 2022/11/9 15:30
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
@Configuration //交给spring管理
public class FormatAutoConfiguration {


	/**
	 *  有多个实现，可以定义一个主的实现
	 */
	@ConditionalOnMissingBean(name = "com.alibaba.fast.json.JSON")
	@Bean
	@Primary
	public IFormatProcessor stringFormat() {
		return new StringFormatProcessor();
	}

	@ConditionalOnBean(name = "com.alibaba.fast.json.JSON")
	@Bean
	public IFormatProcessor jsonFormat() {
		return new JsonFormatProcessor();
	}
}
