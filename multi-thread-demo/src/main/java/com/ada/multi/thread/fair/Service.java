package com.ada.multi.thread.fair;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/27 18:51
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Service {
	private ReentrantLock lock;

	public Service(boolean isFair) {
		lock = new ReentrantLock(isFair);
	}

	public void serviceMethod() {
		try {
			lock.lock();
			System.out.println("线程的名字：" + Thread.currentThread().getName() + "获得锁");
		} finally {
			lock.unlock();
		}
	}
}
