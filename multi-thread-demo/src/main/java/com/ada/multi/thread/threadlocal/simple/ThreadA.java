package com.ada.multi.thread.threadlocal.simple;

import java.util.Date;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/23 23:41
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadA extends Thread {

	@Override
	public void run() {

		//对应ThreadLoacalTest类
		//threadLoacalTest();

		//对应ThreadLoacalTest2类
		threadLoacalTest2();

	}

	private void threadLoacalTest2() {
		for (int i = 0; i < 10; i++) {
			if (ThreadLocalTools.t2.get() == null) {
				ThreadLocalTools.t2.set(new Date());
			}
			System.out.println("线程A " + ThreadLocalTools.t2.get().getTime());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void threadLoacalTest() {
		for (int i = 0; i < 10; i++) {
			ThreadLocalTools.t1.set("ThreadA " + (i + 1));
			System.out.println("ThreadA " + ThreadLocalTools.t1.get());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
