package com.ada.simple.base.priority;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 线程的优先级测试类
 *
 * 线程的优先级能够被继承
 * <p/>
 *
 * @Date: 2023/8/6 13:04
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadPriorityTest {
	public static void main(String[] args) {
		System.out.println("主线程的优先级是："+ Thread.currentThread().getPriority());
		//放开注释也能看到优先级被继承
		Thread.currentThread().setPriority(6);
		System.out.println("主线程的优先级是："+ Thread.currentThread().getPriority());
		MyThread1 thread1 = new MyThread1();
		thread1.start();
	}
}
