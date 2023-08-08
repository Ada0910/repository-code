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
public class MyThreadA extends Thread {
	private Service2 service;

	public MyThreadA(Service2 service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.a();
	}
}
