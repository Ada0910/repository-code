package com.ada.multi.thread.notify;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/16 23:29
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Service {

	public void testMethod(Object lock) {
		try {
			synchronized (lock) {
				System.out.println("testMethod方法中调用 wait：开始执行：" + Thread.currentThread().getName());
				//lock.wait();
				lock.wait(2000);
				System.out.println("testMethod方法中调用 wait：执行完毕：" + Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
