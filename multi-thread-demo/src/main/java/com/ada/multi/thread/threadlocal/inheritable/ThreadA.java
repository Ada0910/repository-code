package com.ada.multi.thread.threadlocal.inheritable;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/24 23:45
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadA extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("在ThreadA中取到的值是：" + Tools.t1.get());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
