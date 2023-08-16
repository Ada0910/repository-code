package com.ada.multi.thread.notify;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/16 23:45
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class NotifyThread extends Thread {

	private Object lock;

	public NotifyThread(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		synchronized (lock) {
			lock.notify();
			//lock.notify();
			//lock.notify();

			//唤醒全部的线程
			//lock.notifyAll();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Object lock = new Object();
		ThreadA a = new ThreadA(lock);
		a.start();

		ThreadB  b = new ThreadB(lock);
		b.start();


		ThreadC c = new ThreadC(lock);
		c.start();

		Thread.sleep(1000);

		NotifyThread notifyThread = new NotifyThread(lock);
		notifyThread.start();
	}
}
