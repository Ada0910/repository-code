package com.ada.multi.thread.synchronize.listen;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/8 22:53
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadA extends Thread {
	private Service service;

	public ThreadA(Service service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.setValue("a", "password");
	}
}
