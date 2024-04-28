package com.ada.multi.thread.keyword.synchronize.obj;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/6 23:15
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadC extends Thread {
	private MyObject object;

	public ThreadC(MyObject object) {
		this.object = object;
	}

	@Override
	public void run() {
		object.methodB();
	}
}
