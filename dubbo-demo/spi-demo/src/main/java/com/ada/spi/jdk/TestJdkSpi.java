package com.ada.spi.jdk;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *  JDK的SPI扩展测试类:
 *
 *
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
