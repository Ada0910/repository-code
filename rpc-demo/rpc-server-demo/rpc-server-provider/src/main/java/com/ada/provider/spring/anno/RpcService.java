package com.ada.provider.spring.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * RPC自定义注解
 * <p/>
 *
 * @Date: 2022/10/15 0:01
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
//修饰类、接口
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component//被spring解析
public @interface RpcService {

	// 拿到服务的接口
	Class<?> value();

	//版本号
	String version() default  "";
}
