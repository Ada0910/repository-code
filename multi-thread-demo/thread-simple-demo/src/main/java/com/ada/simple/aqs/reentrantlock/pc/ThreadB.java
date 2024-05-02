package com.ada.simple.aqs.reentrantlock.pc;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/27 17:33
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadB extends Thread {

	private MyService service;



	public ThreadB(MyService service) {
		this.service = service;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			service.set();
		}
	}
}
