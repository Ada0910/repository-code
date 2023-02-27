package com.ada.spi.jdk;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *  JDK的SPI扩展测试类:
 *
 *  需要在新建对应的目录：META-INF/services/com.ada.spi.jdk.ISayHello（固定）
 *  内容是接口的实现类：com.ada.spi.jdk.SayHelloImpl
 * <p/>
 *
 * @Date: 2023/2/25 16:39
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class TestJdkSpi {
	public static void main(String[] args) {
		ServiceLoader<ISayHello> serviceLoader = ServiceLoader.load(ISayHello.class);
		Iterator<ISayHello> iterator = serviceLoader.iterator();
		while (iterator.hasNext()) {
			iterator.next().sayHello();
		}

	}
}
