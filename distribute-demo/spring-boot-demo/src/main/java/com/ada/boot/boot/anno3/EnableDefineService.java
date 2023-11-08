package com.ada.boot.boot.anno3;

import java.lang.annotation.*;

import org.springframework.context.annotation.Import;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/6 17:34
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CacheImportSelector.class, LoggerDefinitionRegistrar.class})
public @interface EnableDefineService {
	//配置一些方法
	Class<?>[] exclude() default {};
}
