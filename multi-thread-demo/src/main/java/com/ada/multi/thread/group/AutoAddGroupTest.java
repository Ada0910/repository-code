package com.ada.multi.thread.group;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 线程自动归属特性
 * <p/>
 *
 * @Date: 2023/8/30 23:03
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class AutoAddGroupTest {
	public static void main(String[] args) {
		System.out.println("A出线程：" + Thread.currentThread().getName() + ",所属的线程组名：" + Thread.currentThread().getThreadGroup().getName() + ",线程中的数量有：" + Thread.currentThread().getThreadGroup().activeGroupCount());
		ThreadGroup group = new ThreadGroup("一个新数组");
		System.out.println("B出线程：" + Thread.currentThread().getName() + ",所属的线程组名：" + Thread.currentThread().getThreadGroup().getName() + ",线程中的数量有：" + Thread.currentThread().getThreadGroup().activeGroupCount());
		ThreadGroup[] threadGroups = new ThreadGroup[Thread.currentThread().getThreadGroup().activeCount()];
		// enumerate 将线程组中的子线程组一复制的形式拷贝到threadGroups[i]数组中
		Thread.currentThread().getThreadGroup().enumerate(threadGroups);
		for (int i = 0; i < threadGroups.length; i++) {
			System.out.println("第一个线程的名字为：" + threadGroups[i].getName());
		}
	}
}
