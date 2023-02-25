package com.ada.spi.jdk;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *  ISayHello 的接口实现类
 *
 * JDK扩展：
 * 在resources中，新建如下的目录：META-INF/services/com.ada.spi.jdk.ISayHello
 * 内容是：com.ada.spi.jdk.SayHelloImpl
 *
 * <p/>
 *
 * @Date: 2023/2/25 12:50
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SayHelloImpl implements ISayHello {
	@Override
	public void sayHello() {
		System.out.println("我是ISayHello的接口实现类！");
	}
}
