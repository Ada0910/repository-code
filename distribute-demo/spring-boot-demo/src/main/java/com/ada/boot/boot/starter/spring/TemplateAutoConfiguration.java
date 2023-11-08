package com.ada.boot.boot.starter.spring;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ada.boot.boot.starter.FormatTemplate;
import com.ada.boot.boot.starter.config.Properties;
import com.ada.boot.boot.starter.format.IFormatProcessor;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * 为什么要有这个文件
 * 因为配置这个，可以把FormatTemplate作为bean交给spring去管理，所以在后面需要用的时候，直接使用@Autowired
 * 在IOC容器中加载这个bean
 *
 * <p/>
 * <b>Creation Time:</b> 2022/11/9 15:38
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
@Import(FormatAutoConfiguration.class)
@EnableConfigurationProperties(Properties.class)
@Configuration
public class TemplateAutoConfiguration {

	/**
	 *  @Configuration和@Bean说明是将这个注入到了IOC容器中
	 *  那如果要@Autowired这个，就可以在IOC容器中找到了对应的实例，也就是可以使用这个bean
	 *  简直完美
	 */
	@Bean
	public FormatTemplate getFormatTemplate(IFormatProcessor formatProcessor, Properties properties) {
		return new FormatTemplate(formatProcessor,properties);
	}


}
