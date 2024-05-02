package com.ada.simple.status.sleep;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * Thread.sleep不释放锁
 * <p/>
 *
 * @Date: 2023/8/20 19:13
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SleepLockTest {
	public static void main(String[] args) throws InterruptedException {
		ThreadB b = new ThreadB();
		ThreadA a = new ThreadA(b);
		a.start();

		Thread.sleep(2000);

		ThreadC c = new ThreadC(b);
		c.start();
	}
}
