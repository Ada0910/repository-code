package com.ada.simple.base.unsafe;

import sun.misc.Unsafe;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 对象操作
 * <p/>
 *
 * @Date: 2023/6/24 0:02
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ObjectOperatorDemo {
	private int value;

	public static void main(String[] args) throws Exception {
		Unsafe unsafe = UnsafeInstance.getUnsafeInstance();
		assert unsafe != null;
		long offset = unsafe.objectFieldOffset(ObjectOperatorDemo.class.getDeclaredField("value"));
		ObjectOperatorDemo main = new ObjectOperatorDemo();
		System.out.println("value before putInt: " + main.value);
		unsafe.putInt(main, offset, 42);
		System.out.println("value after putInt: " + main.value);
		System.out.println("value after putInt: " + unsafe.getInt(main, offset));
	}

}
