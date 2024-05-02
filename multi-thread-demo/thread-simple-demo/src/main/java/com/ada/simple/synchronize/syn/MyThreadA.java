package com.ada.simple.synchronize.syn;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/7 23:47
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyThreadA extends Thread {
	private Task task;

	public MyThreadA(Task task) {
		this.task = task;
	}

	@Override
	public void run() {
		task.doLongTimeTask();
	}
}
