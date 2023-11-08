package com.ada.multi.thread.keyword.synchronize.obj;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/6 18:38
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadA extends Thread {
	private MyObject object;

	public ThreadA(MyObject object) {
		this.object = object;
	}

	@Override
	public void run() {
		object.methodA();
	}
}
