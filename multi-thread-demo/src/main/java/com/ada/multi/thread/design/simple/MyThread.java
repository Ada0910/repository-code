package com.ada.multi.thread.design.simple;

import java.util.concurrent.CountDownLatch;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/8/29 16:22
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class MyThread  extends  Thread{
	CountDownLatch countDownLatch ;
	public MyThread(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		//饿汉式
		//System.out.println("hashCode是："+ HungryObject.getInstance().hashCode());
		//懒汉式
		//System.out.println("hashCode是："+ LazyObject.getInstance().hashCode());
		// 静态内部类
		System.out.println("hashCode是："+ StaticObject.getInstance().hashCode());
		countDownLatch.countDown();
	}


}
