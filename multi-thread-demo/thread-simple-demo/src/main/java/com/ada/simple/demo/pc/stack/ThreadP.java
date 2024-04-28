package com.ada.multi.thread.demo.pc.stack;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 12:49
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadP extends Thread {
	public P p;

	public ThreadP(P p) {
		this.p = p;
	}

	@Override
	public void run() {
		while (true) {
			p.pushService();
		}
	}
}
