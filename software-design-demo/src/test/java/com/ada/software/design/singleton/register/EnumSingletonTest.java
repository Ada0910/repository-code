package com.ada.software.design.singleton.register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *  EnumSingleton测试类
 * <p/>
 *
 * @Date: 2022/9/17 21:46
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class EnumSingletonTest {
	public static void main(String[] args) {
		// testSeriable();

		testReflect();

	}

	/**
	 * 测试反射能否破坏枚举式单例
	 */
	private static void testReflect() {
		try {
			Class<?> clazz = EnumSingleton.class;
			Constructor c = clazz.getDeclaredConstructor(String.class,int.class);
			c.setAccessible(true);
			EnumSingleton enumSingleton = (EnumSingleton)c.newInstance("Tom",666);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试序列化是否能够破坏单例
	 */
	private static void testSeriable() {
		try {
			EnumSingleton instance1 = null;

			EnumSingleton instance2 = EnumSingleton.getInstance();
			instance2.setData(new Object());

			FileOutputStream fos = new FileOutputStream("EnumSingleton.obj");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(instance2);
			oos.flush();
			oos.close();

			FileInputStream fis = new FileInputStream("EnumSingleton.obj");
			ObjectInputStream ois = new ObjectInputStream(fis);
			instance1 = (EnumSingleton) ois.readObject();
			ois.close();

			System.out.println(instance1.getData());
			System.out.println(instance2.getData());
			System.out.println(instance1.getData() == instance2.getData());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
