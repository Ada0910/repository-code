package com.ada.multi.thread.keyword.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/27 12:28
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public abstract class MyService {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();


	/**
	 * 会报错的方法
	 */
	public void await() {
		try {
			condition.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 正确的方法
	 */

	public void waitMethod() {
		lock.lock();
		System.out.println("线程执行了waitMethod方法");
		try {
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("finally方法中释放锁了！");
		}
		System.out.println("已执行condition.await()");
	}

	public void signal() {
		try {
			lock.lock();
			System.out.println("执行了signal方法");
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}
