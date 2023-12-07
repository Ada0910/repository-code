package com.ada.software.design.proxy.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/28 10:25
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class CglibTest {
	public static void main(String[] args) throws Exception {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"E://cglib_proxy_classes");

		Customer obj = (Customer) new GglibMatchmaker().getInstance(Customer.class);
		System.out.println(obj);
		obj.findLove();
	}
}
