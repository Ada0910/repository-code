package com.ada.simple.other.sleep;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 19:12
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadC extends Thread {

	private ThreadB b;

	public ThreadC(ThreadB b) {
		this.b = b;
	}

	@Override
	public void run() {
		b.printService();
	}
}
