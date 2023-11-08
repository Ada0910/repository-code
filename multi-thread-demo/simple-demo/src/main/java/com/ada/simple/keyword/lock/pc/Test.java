package com.ada.multi.thread.keyword.lock.pc;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/27 17:37
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Test {
	public static void main(String[] args) throws InterruptedException {
		MyService service = new MyService();
		//ThreadA a = new ThreadA(service);
		//a.start();
		//
		//Thread .sleep(3000);
		//ThreadB b = new ThreadB(service);
		//b.start();

		//连续多次打印
		ThreadA[] threadArrayA  = new ThreadA[10];
		ThreadB[] threadArrayB  = new ThreadB[10];

		for (int i = 0; i < 10; i++) {
			threadArrayA[i] = new ThreadA(service);
			threadArrayB[i] = new ThreadB(service);
			threadArrayA[i].start();
			threadArrayB[i].start();
		}

	}
}
