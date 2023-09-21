package com.ada.multi.thread.demo.pc.value;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/8/17 18:23
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class ProducerAndConsumerTest {
	public static void main(String[] args) throws InterruptedException {
		String lock = new String("");
		ProducerDemo p = new ProducerDemo(lock);
		ComsumerDemo c = new ComsumerDemo(lock);

		ThreadP[] pThread = new ThreadP[2];
		ThreadC[] cThread = new ThreadC[2];

		for (int i = 0; i < 2; i++) {
			pThread[i] = new ThreadP(p);
			pThread[i].setName("生产者：" + (i + 1));


			cThread[i] = new ThreadC(c);
			cThread[i].setName("消费者：" + (i + 1));
			pThread[i].start();
			cThread[i].start();
		}

		Thread.sleep(5000);

		Thread[] threads = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
		Thread.currentThread().getThreadGroup().enumerate(threads);
		for (int i = 0; i < threads.length; i++) {
			System.out.println("当前线程的名字：" + threads[i].getName() + "     " + threads[i].getState());
		}
	}
}
