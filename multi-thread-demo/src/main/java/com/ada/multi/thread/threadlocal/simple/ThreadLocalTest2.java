package com.ada.multi.thread.threadlocal.simple;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/23 23:46
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadLocalTest2 {
	public static void main(String[] args) throws InterruptedException {
		ThreadA a = new ThreadA();
		ThreadB b = new ThreadB();
		Thread.sleep(200);
		a.start();
		b.start();
	}
}
