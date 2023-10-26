package com.ada.multi.thread.keyword.lock.condition;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/27 12:33
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadA extends Thread {
	private MyService service;
	private  MyService1 myService1;

	public ThreadA(MyService service) {
		this.service = service;
	}

	public ThreadA(MyService1 myService1) {
		this.myService1 = myService1;
	}

	@Override
	public void run() {
		//service.await();
		//service.waitMethod();
		myService1.awaitA();
	}
}
