package com.ada.simple.aqs.reentrantlock.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * <b><code></code></b>
 * <p/>
 * condition唤醒 测试用例
 *  signal:唤醒阻塞的线程
 * <p/>
 * <b>Creation Time:</b> 2022/9/21 15:59
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class ConditionNotifyDemo implements Runnable {

	private Lock lock;
	private Condition condition;

	public ConditionNotifyDemo(Lock lock, Condition condition) {
		this.lock = lock;
		this.condition = condition;
	}

	@Override
	public void run() {
		try {
			//获得了锁.
			lock.lock();
			System.out.println("begin - conditionNotify");
			//唤醒阻塞状态的线程
			condition.signal();

			//condition.await();
			System.out.println("end - conditionNotify");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放锁
			lock.unlock();
		}
	}
}
