package com.ada.multi.thread.base.fair;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/27 18:56
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class FairAndNotFairTest {
	public static void main(String[] args) {
		//公平锁
		//Service service = new Service(true);
		// 非公平锁
		Service service = new Service(false);

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("线程" +Thread.currentThread().getName() + "运行了");
				service.serviceMethod();
			}
		};

		Thread[] threads = new Thread[20];
		for (int i = 0; i < 20; i++) {
			threads[i] = new Thread(runnable);
		}

		for (int i = 0; i < 20; i++) {
			threads[i].start();
		}
	}
}
