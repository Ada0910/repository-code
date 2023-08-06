package com.ada.multi.thread.priority;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/6 12:59
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyThread1 extends Thread {
	@Override
	public void run() {
		System.out.println(currentThread().getName() + "：【MyThread1】的优先级是；" + this.getPriority());
		MyThread2 thread2 = new MyThread2();
		thread2.start();
	}
}
