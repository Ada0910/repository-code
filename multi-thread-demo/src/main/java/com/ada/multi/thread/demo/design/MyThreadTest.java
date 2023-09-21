package com.ada.multi.thread.demo.design;

import java.util.concurrent.CountDownLatch;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/8/29 16:25
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class MyThreadTest {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch  =new CountDownLatch(6);
		long beginTime =System.currentTimeMillis();
		MyThread a = new MyThread(countDownLatch);
		MyThread b = new MyThread(countDownLatch);
		MyThread c = new MyThread(countDownLatch);
		a.start();
		b.start();
		c.start();
		MyThread aa = new MyThread(countDownLatch);
		MyThread bb = new MyThread(countDownLatch);
		MyThread cc = new MyThread(countDownLatch);
		aa.start();
		bb.start();
		cc.start();
		countDownLatch.await();
		long endTime = System.currentTimeMillis();
		System.out.println("一共消耗了：" + (endTime-beginTime)/1000 + "秒");
		System.out.println("一共消耗了：" + (endTime-beginTime) + "毫秒");
	}
}
