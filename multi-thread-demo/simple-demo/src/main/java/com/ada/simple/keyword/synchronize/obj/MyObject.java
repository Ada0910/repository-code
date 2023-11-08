package com.ada.multi.thread.keyword.synchronize.obj;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 证明线程锁的是对象
 * <p/>
 *
 * @Date: 2023/8/6 18:37
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyObject {
	public synchronized void methodA() {
		System.out.println("开始执行方法A：" + Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("方法A执行完毕");
	}

	//方法B没有加synchronized关键字
	public synchronized void methodB() {
		System.out.println("开始执行方法B：" + Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("方法B执行完毕");
	}
}
