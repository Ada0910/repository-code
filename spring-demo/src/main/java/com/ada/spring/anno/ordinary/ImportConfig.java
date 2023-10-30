package com.ada.spring.anno.ordinary;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 导入配置类
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 17:16
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
@Configuration
@Import(OrdinaryClassImport.class)
public class ImportConfig {
}
