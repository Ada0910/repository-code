package com.ada.simple.aqs.reentrantlock.util;

import java.util.concurrent.CountDownLatch;

/**
 *
 * <b><code></code></b>
 * <p/>
 * countdownlatch 是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的操作执行完毕再执行
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/21 16:32
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class CountDownLatchDemo extends Thread {
	/**
	 * countdownlatch 提供了两个方法
	 * 一个是 countDown，countdownlatch 初始化的时候需要传入一个整数，在这个整数倒数到 0 之前
	 * 调用了 await 方法的程序都必须要等待，然后通过 countDown 来倒数
	 *
	 * 一个是 await
	 */
	static CountDownLatch countDownLatch = new CountDownLatch(100);

	@Override
	public void run() {
		try {
			//阻塞  3个线程 Thread.currentThread
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//TODO
		System.out.println("ThreadName:" + Thread.currentThread().getName());
	}

	public static void main(String[] args) throws Exception {
		simpleDemo();
		//hightConcurrnetDemo();

	}

	/**
	 * 简单的测试样例
	 *
	 * @param
	 * @return void
	 * @throws Exception
	 * @Date 16:41 2022/9/21
	 * @since  1.0.0.1
	 */
	private static void simpleDemo() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		new Thread(() -> {
			System.out.println("Thread1");
			countDownLatch.countDown(); //3-1=2
		}).start();
		new Thread(() -> {
			System.out.println("Thread2");
			countDownLatch.countDown();//2-1=1
		}).start();
		new Thread(() -> {
			System.out.println("Thread3");
			countDownLatch.countDown();//1-1=0
		}).start();
		countDownLatch.await();
	}

	/**
	 * 模拟高并发的场景
	 *
	 * @param
	 * @return void
	 * @throws Exception
	 * @Date 16:41 2022/9/21
	 * @since  1.0.0.1
	 */
	private static void hightConcurrnetDemo() {
		for (int i = 0; i < 3; i++) {
			new CountDownLatchDemo().start();
		}
		countDownLatch.countDown();
	}

}
