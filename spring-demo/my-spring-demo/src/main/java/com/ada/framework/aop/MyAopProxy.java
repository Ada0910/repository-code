package com.ada.framework.aop;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/5/11 22:06
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public interface MyAopProxy {

	Object getProxy();

	Object getProxy(ClassLoader classLoader);
}
