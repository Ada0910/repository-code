package com.ada.multi.thread.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * <b><code></code></b>
 * <p/>
 * ConditionWait 测试样例
 * await:把当前线程阻塞挂起
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/21 15:53
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since 1.0.0.1
 */
public class ConditionWaitDemo implements Runnable {
	private Lock lock;
	private Condition condition;

	public ConditionWaitDemo(Lock lock, Condition condition) {
		this.lock = lock;
		this.condition = condition;
	}

	@Override
	public void run() {
		try {
			lock.lock(); //竞争锁
			System.out.println("begin - ConditionWait");
			try {
				condition.await();//阻塞(1. 释放锁, 2.阻塞当前线程, FIFO（单向、双向）)
				System.out.println("end - ConditionWait");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			lock.unlock();
		}
	}
}
