package com.ada.multi.thread.pc.stack;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 12:59
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadC extends Thread {

	private C c;

	public ThreadC(C c) {
		this.c = c;
	}

	@Override
	public void run() {
		c.popService();
	}
}
