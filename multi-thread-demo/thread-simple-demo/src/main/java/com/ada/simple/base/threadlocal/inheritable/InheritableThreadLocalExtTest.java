package com.ada.simple.base.threadlocal.inheritable;

/**
 *
 * <b><code></code></b>
 * <p/>
 * InheritableThreadLocal类可以让子线程中取得父线程继承下来的值
 * <p/>
 *
 * @Date: 2023/8/24 23:46
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class InheritableThreadLocalExtTest {
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			System.out.println("在Main线程中取值：" + Tools.t1.get());
			Thread.sleep(200);
		}
		Thread.sleep(2000);

		ThreadA a  =new ThreadA();
		a.start();
	}
}
