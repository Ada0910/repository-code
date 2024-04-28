package com.ada.multi.thread.communication.lock;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 19:03
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadA extends Thread {

	private ThreadB b;

	public ThreadA(ThreadB b) {
		this.b = b;
	}

	@Override
	public void run() {
		try {
			synchronized (b) {
				b.start();
				// sleep不释放锁
				Thread.sleep(6000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
