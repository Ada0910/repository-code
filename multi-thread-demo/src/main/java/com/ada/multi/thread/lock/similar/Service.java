package com.ada.multi.thread.lock.similar;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/8/29 15:35
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class Service {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void read() {
		try {
			try {
				lock.readLock().lock();
				System.out.println("获得读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				Thread.sleep(10000);
			} finally {
				//读读共享
				lock.readLock().unlock();

				//	读写互斥
				//lock.writeLock().lock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}

	public void write() {
		try {
			try {
				lock.writeLock().lock();
				System.out.println("获得写锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				Thread.sleep(10000);
			} finally {
				//读读共享
				lock.writeLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
}
