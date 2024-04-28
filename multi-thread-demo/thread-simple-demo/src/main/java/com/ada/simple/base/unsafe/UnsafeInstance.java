package com.ada.multi.thread.base.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 反射获得 Unsafe 类中已经实例化完成的单例对象 theUnsafe
 * <p/>
 *
 * @Date: 2023/6/23 23:32
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class UnsafeInstance {

	/**
	 * 获取Unsafe实例
	 */
	public static Unsafe getUnsafeInstance() {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			return (Unsafe) field.get(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
