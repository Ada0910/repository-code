package com.ada.software.design.proxy.jdk;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
//import sun.misc.ProxyGenerator;

import com.ada.software.design.proxy.Person;

/**
 *
 * <b><code></code></b>
 * <p/>
 * JDK动态代理测试类
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 15:05
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class JDKProxyTest {
	public static void main(String[] args) throws Exception {
		Object obj = new JdkMatchmaker().getInstance(new Girl());
		Method m = obj.getClass().getMethod("findLove", null);
		m.invoke(obj);

		//通过反编译工具可以查看源代码
		//byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
		//FileOutputStream os = new FileOutputStream("E://$Proxy0.class");
		//os.write(bytes);
		//os.close();
	}
}
