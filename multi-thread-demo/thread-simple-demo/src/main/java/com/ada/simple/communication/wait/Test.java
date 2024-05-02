package com.ada.simple.communication.wait;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/14 23:18
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Test {
	public static void main(String[] args) {
		Object lock = new Object();
		WaitDemo waitDemo = new WaitDemo(lock);
		waitDemo.start();

		NotifyDemo notifyDemo = new NotifyDemo(lock);
		notifyDemo.start();

	}
}
