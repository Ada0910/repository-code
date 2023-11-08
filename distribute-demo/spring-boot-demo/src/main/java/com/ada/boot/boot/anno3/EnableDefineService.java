package com.ada.boot.boot.anno3;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

import com.ada.distribute.boot.anno3.LoggerDefinitionRegistrar;

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
