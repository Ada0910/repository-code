package com.ada.simple.demo.fork;

import java.util.concurrent.CountDownLatch;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 
 * Java工程在主线程执行期间开一个子线程执行任务，主线程等待子线程成功后继续执行
 * 如果是需要多个线程并行处理的，可以使用CountDownLanch来进行处理。（CountDownLanch 是一个倒数计数器, 给一个初始值(>=0), 然后每一次调用countDown就会减1, 这很符合等待多个子线程结束的场景: 一个线程结束的时候, countDown一次, 直到所有的线程都countDown了 , 那么所有子线程就都结束了.）
 * 
 * <p/>
 * <b>Creation Time:</b> 2023/12/7 14:13
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class ForkSubThread4 {
	public static void main(String[] args) throws Exception {
		//定义线程数
		int subThreadNum = 5;
		//取得一个倒计时器，从5开始
		CountDownLatch countDownLatch = new CountDownLatch(subThreadNum);
		//依次创建5个线程，并启动
		for (int i = 0; i < subThreadNum; i++) {
			CustomRunnable customRunnable = new CustomRunnable(i,countDownLatch);
			Thread thread = new Thread(customRunnable,"子线程");
			thread.start(); //子线程执行
		}
		System.out.println("主线程做自己的事情--start");
		//等待所有的子线程结束
		countDownLatch.await();
		System.out.println("主线程做自己的事情--end");
	}

	static final class CustomRunnable implements Runnable {
		private int i;
		private CountDownLatch countDownLatch;
		public CustomRunnable(int i, CountDownLatch countDownLatch) {
			this.i = i;
			this.countDownLatch = countDownLatch;
		}
		private String a = "";
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + ":执行 start");
				Thread.sleep(2000); //子线程停留2秒
				System.out.println(Thread.currentThread().getName() + ":执行 end");
				a = "sub:" + i;
				System.out.println(a);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				//线程结束时，将计时器减一
				countDownLatch.countDown();
			}
		}
		private String getData() {
			return a;
		}
	}
}