package com.ada.simple.demo.fork;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 
 * execute无返回值
 * submit和Future 是有返回值的
 * 
 * <p/>
 * <b>Creation Time:</b> 2023/12/7 15:05
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since 1.0.0.1
 */
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThreadAwaitThreadPool {
	public static void main(String[] args) {
		// 创建线程池
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		// 创建CountDownLatch，设置计数器为任务数量
		CountDownLatch countDownLatch = new CountDownLatch(10);

		// 提交任务给线程池执行
		for (int i = 0; i < 10; i++) {
			Runnable task = new Task(countDownLatch);
			executorService.execute(task);
		}

		try {
			// 主线程等待所有任务执行完毕
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 所有任务执行完毕后，继续执行主线程的逻辑
		System.out.println("All tasks have been completed.");

		// 关闭线程池
		executorService.shutdown();
	}
}

class Task implements Runnable {
	private final CountDownLatch countDownLatch;

	public Task(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		try {
			// 模拟任务执行
			Thread.sleep(1000);
			System.out.println("Task executed by " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 任务执行完毕后，调用countDown()方法
			countDownLatch.countDown();
		}
	}
}
