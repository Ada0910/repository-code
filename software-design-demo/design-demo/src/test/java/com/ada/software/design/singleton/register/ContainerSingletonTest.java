package com.ada.software.design.singleton.register;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/9/17 23:33
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ContainerSingletonTest {
	public static void main(String[] args) {
		Object bean = ContainerSingleton.getBean("com.ada.software.design.singleton.lazy.LazyDoubleCheckSingletonTest");
		System.out.println(bean);
	}
}
