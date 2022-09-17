package com.ada.software.design.singleton.lazy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * LazyInnerClassSingleton测试类
 * <p/>
 *
 * @Date: 2022/9/17 19:23
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class LazyInnerClassSingletonTest {
	public static void main(String[] args) {
		try {
			Class<?> clazz = LazyInnerClassSingleton.class;
			//通过反射拿到私有的构造方法
			Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
			//强制访问，不愿意也是要访问的
			declaredConstructor.setAccessible(true);
			//暴力初始化
			Object o = declaredConstructor.newInstance();
			//调用了两次构造方法，相当于new了两次
			//犯了原则性问题，
			Object o2 = declaredConstructor.newInstance();

			System.out.println(declaredConstructor == o2);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
