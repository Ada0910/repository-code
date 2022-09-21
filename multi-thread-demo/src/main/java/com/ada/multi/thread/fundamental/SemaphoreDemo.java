package com.ada.multi.thread.fundamental;

import java.util.concurrent.Semaphore;

/**
 *
 * <b><code></code></b>
 * <p/>
 * semaphore 也就是我们常说的信号灯
 * semaphore 可以控制同时访问的线程个数，通过 acquire 获取一个许可，如果没有就等待，通过 release 释放一个许可。有点类似限流的作用
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/21 16:47
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class SemaphoreDemo {


	public static void main(String[] args) {
		Semaphore semaphore=new Semaphore(5);
		for(int i=0;i<10;i++){
			new Car(i,semaphore).start();
		}
	}

	static class Car extends Thread {
		private int num;
		private Semaphore semaphore;

		public Car(int num, Semaphore semaphore) {
			this.num = num;
			this.semaphore = semaphore;
		}

		public void run() {
			try {
				//获得一个令牌, 如果拿不到令牌，就会阻塞
				semaphore.acquire();
				System.out.println("第" + num + " 抢占一个车位");
				Thread.sleep(2000);
				System.out.println("第" + num + " 开走喽");
				semaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
