package com.ada.multi.thread.aqs;

import java.util.concurrent.locks.Lock;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/9/21 10:44
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class TwinsLockTest {
	public static void main(String[] args) throws Exception {
		final Lock lock = new TwinsLock();
		class Worker extends Thread {
			public void run() {
				while (true) {
					lock.lock();
					try {
						//SleepUtils.second(1);
						//Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName());
						//SleepUtils.second(1);
						//Thread.sleep(1000);
					} finally {
						lock.unlock();
					}
				}
			}
		}
// 启动10个线程
		for (int i = 0; i < 10; i++) {
			Worker w = new Worker();
			w.setDaemon(true);
			w.start();
		}
// 每隔1秒换行
		for (int i = 0; i < 10; i++) {
			//SleepUtils.second(1);
			Thread.sleep(1000);
			System.out.println();
		}

	}
}
