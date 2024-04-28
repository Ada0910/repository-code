package com.ada.multi.thread.base.atom;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/10/27 15:08
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class AtomicIntegerFieldUpdaterTest {
	
	// 创建原子更新器,并设置需要更新的对象类和对象的属性
	private static AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.
			newUpdater(User.class, "old");

	public static void main(String[] args) {
// 设置柯南的年龄是10岁
		User conan = new User("conan", 10);
// 柯南长了一岁,但是仍然会输出旧的年龄
		System.out.println(a.getAndIncrement(conan));
// 输出柯南现在的年龄
		System.out.println(a.get(conan));
	}
}
