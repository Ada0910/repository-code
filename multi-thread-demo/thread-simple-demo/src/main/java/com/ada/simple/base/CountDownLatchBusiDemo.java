package com.ada.multi.thread.base;

import java.util.concurrent.CountDownLatch;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * CountDownLatch 业务场景：
 * 顾名思义CountdownLatch可以当做一个计数器来使用，比如某线程需要等待其他几个线程都执行过某个时间节点后才能继续执行
 *
 *
 * 我们来模拟一个场景
 * 某公司一共有十个人,门卫要等十个人都来上班以后,才可以休息
 *
 * CountDownLatch类似于10人找了一个代购，代购收齐了10个客户的钱才买票出国购物，
 * 而CyclicBarrier类似10个拼团购物，人齐了之后开团了，这10个自己买自己的哈
 * <p/>
 *
 * @Date: 2023/4/22 23:45
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class CountDownLatchBusiDemo {
	public static void main(String[] args) {
		//可以看到子线程并没有因为调用latch.countDown而阻塞,会继续进行该做的工作,只是通知计数器-1,即完成了我们如上说的场景
		// 只需要在所有进程都进行到某一节点后才会执行被阻塞的进程
		// 如果我们想要多个线程在同一时间进行就要用到CyclicBarrier了

		final CountDownLatch latch = new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			final int times = i;
			new Thread(() -> {
				System.out.println("子线程" + Thread.currentThread().getName() + "正在赶路");
				try {
					Thread.sleep(1000 * times);
					System.out.println("子线程" + Thread.currentThread().getName() + "到公司了");
					//调用latch的countDown方法使计数器-1
					latch.countDown();
					System.out.println("子线程" + Thread.currentThread().getName() + "开始工作");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}).start();
			System.out.println("门卫等待员工上班中...");
			//主线程阻塞等待计数器归零
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("员工都来了,门卫去休息了");

		}
	}
}
