package com.ada.multi.thread.keyword.lock.pc;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/27 17:24
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadA extends Thread{
	private  MyService myService ;

	public ThreadA(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			myService.get();
		}

	}
}
