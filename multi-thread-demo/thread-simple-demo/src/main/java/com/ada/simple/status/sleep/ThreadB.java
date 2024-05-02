package com.ada.simple.status.sleep;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 19:04
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadB extends Thread {

	@Override
	public void run() {
		System.out.println("线程类B开始的时间：" + System.currentTimeMillis());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程B执行代码后的时间是：" + System.currentTimeMillis());
	}

	public synchronized  void printService(){
		System.out.println("printService打印了的方法执行的时间是：" + System.currentTimeMillis());
	}
}
