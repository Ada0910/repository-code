package com.ada.spring.anno.registrar;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ada.spring.anno.selector.ImportSelectorConfig;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 导入配置类
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 18:01
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
@Import(ImportBeanDefinitionRegistrarConfig.class)
@Configuration
public class ImportConfig {
	
}
