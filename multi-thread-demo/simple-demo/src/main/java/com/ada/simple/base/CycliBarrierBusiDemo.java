package com.ada.multi.thread.base;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * <b><code></code></b>
 * <p/>
 * CycliBarrier的业务场景
 *
 * 用已经被说烂的跑步场景吧
 * 十名运动员各自准备比赛,需要等待所有运动员都准备好以后,裁判才能说开始然后所有运动员一起跑,
 *
 * CountDownLatch类似于10人找了一个代购，代购收齐了10个客户的钱才买票出国购物，
 * 而CyclicBarrier类似10个拼团购物，人齐了之后开团了，这10个自己买自己的哈
 * <p/>
 *
 * @Date: 2023/4/22 23:58
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class CycliBarrierBusiDemo {
	public static void main(String[] args) {

		//可以看到所有线程在其他线程没有准备好之前都在被阻塞中,
		// 等到所有线程都准备好了才继续执行
		// 我们在创建CyclicBarrier对象时传入了一个方法,
		// 当调用CyclicBarrier的await方法后,当前线程会被阻塞等到所有线程都调用了await方法后 调用传入CyclicBarrier的方法
		// 然后让所有的被阻塞的线程一起运行

		final CyclicBarrier cyclicBarrier = new CyclicBarrier(10,()->{
			System.out.println("所有人都准备好了裁判开始了");
		});

		for (int i = 0; i < 10; i++) {
			final int times = i;
			new Thread(()->{
				try {
					System.out.println("子线程" + Thread.currentThread().getName() + "正在准备");
					Thread.sleep(1000 * times);
					System.out.println("子线程" + Thread.currentThread().getName() + "准备好了");
					cyclicBarrier.await();
					System.out.println("子线程" + Thread.currentThread().getName() + "开始跑了");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
