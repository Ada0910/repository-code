package com.ada.simple.status.interrupt;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * <b><code></code></b>
 * <p/>
 * getHoldCount 调用lock()的次数
 *  getQueueLength 正等待获取此锁定的线程估计数
 * <p/>
 * <b>Creation Time:</b> 2023/8/28 15:52
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class SimpleService {
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void serviceMethod() {
		try {
			lock.lock();
			System.out.println("serviceMethod getHoldCount:" + lock.getHoldCount());
			serviceMethod2();
		} finally {
			lock.unlock();
		}
	}

	private void serviceMethod2() {
		try {
			lock.lock();
			System.out.println("serviceMethod2 getHoldCount:" + lock.getHoldCount());
		} finally {
			lock.unlock();
		}
	}


	private void serviceMethod3() {
		try {
			lock.lock();
			System.out.println("线程：" + Thread.currentThread().getName() + "进入方法！");
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}


	private void serviceMethod4() {
		try {
			lock.lock();
			condition.await();
			System.out.println("serviceMethod4 当前线程保持此锁定的个数:" + lock.getHoldCount());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	private void notifyMethod() {
		try {
			lock.lock();
			System.out.println("有" + lock.getWaitQueueLength(condition) + "个线程正在等待condition");
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		//测试用例一
		//SimpleService service = new SimpleService();
		//service.serviceMethod();

		//getQueueLength 测试用例
		//SimpleService service = new SimpleService();
		//Runnable runnable = new Runnable() {
		//	@Override
		//	public void run() {
		//		service.serviceMethod3();
		//	}
		//};
		//
		//
		//Thread[] threads = new Thread[10];
		//for (int i = 0; i < 10; i++) {
		//	threads[i] = new Thread(runnable);
		//}
		//
		//for (int i = 0; i < 10; i++) {
		//	threads[i].start();
		//}
		//Thread.sleep(2000);
		//
		//System.out.println("有线程数：" + service.lock.getQueueLength() + "，在等待获取锁！");


		//getWaitQueueLength 测试用例

		SimpleService service = new SimpleService();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.serviceMethod4();
			}
		};


		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(runnable);
		}

		for (int i = 0; i < 10; i++) {
			threads[i].start();
		}
		Thread.sleep(2000);
		service.notifyMethod();
	}
	
}
