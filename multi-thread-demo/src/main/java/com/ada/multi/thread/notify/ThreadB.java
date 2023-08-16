package com.ada.multi.thread.notify;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/16 23:40
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadB extends Thread {
	public Object lock;

	public ThreadB(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		Service service = new Service();
		service.testMethod(lock);
	}
}
