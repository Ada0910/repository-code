package com.ada.multi.thread.keyword.lock.condition;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/27 16:37
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadB extends Thread {
	private MyService1 myService1;

	public ThreadB(MyService1 myService1) {
		this.myService1 = myService1;
	}

	@Override
	public void run() {
		myService1.awaitB();
	}
}
