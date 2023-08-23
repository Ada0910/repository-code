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
public class ThreadLocalTest {
	public static void main(String[] args) throws InterruptedException {
		ThreadA a = new ThreadA();
		ThreadB b = new ThreadB();
		a.start();
		b.start();
		for (int i = 0; i < 10; i++) {
			ThreadLocalTools.t1.set("主线程" + (i + 1));
			System.out.println("主线程的值：" + ThreadLocalTools.t1.get());
			Thread.sleep(200);
		}
	}
}
