package com.ada.simple.communication.notify;

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
public class ThreadC extends Thread {
	public Object lock;

	public ThreadC(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		Service service = new Service();
		service.testMethod(lock);
	}
}
