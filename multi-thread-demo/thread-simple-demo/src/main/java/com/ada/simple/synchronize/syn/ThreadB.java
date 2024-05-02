package com.ada.simple.synchronize.syn;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/7 23:37
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadB extends Thread {
	private ObjectService service;

	public ThreadB(ObjectService service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.serviceMethodB();
	}
}
