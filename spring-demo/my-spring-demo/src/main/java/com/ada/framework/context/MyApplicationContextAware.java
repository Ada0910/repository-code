package com.ada.framework.context;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 通过解耦的方式，获取IOC容器的顶层设计
 *
 * 后面将通过一个监听器去扫描所有的类，只要实现了接口
 * 将自动调用setApplicationContext()方法，从而将IOC容器注入到目标类中
 * <p/>
 *
 * @Date: 2023/5/1 12:56
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public interface MyApplicationContextAware {
	void setApplicationContext(MyApplicationContext context);
}
