package com.ada.multi.thread.keyword.lock.similar;


/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/8/29 15:37
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class ServiceTest {
	public static void main(String[] args) throws InterruptedException {
		Service service = new Service();
		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();

		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();

		//Thread.sleep(2000);
	}
}
