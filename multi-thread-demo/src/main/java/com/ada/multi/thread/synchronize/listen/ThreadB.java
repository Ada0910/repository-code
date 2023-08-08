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
public class ThreadB extends Thread {
	private Service service;

	public ThreadB(Service service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.setValue("name", "b");
	}
}
