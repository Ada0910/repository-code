package com.ada.multi.thread.fundamental;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * <b><code></code></b>
 * <p/>
 * CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也
 * 可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续工作
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/21 16:53
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class CycliBarrierDemo extends Thread {
	@Override
	public void run() {
		System.out.println("开始进行数据分析");
	}

	//循环屏障
	//可以使得一组线程达到一个同步点之前阻塞.

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier
				(3, new CycliBarrierDemo());
		new Thread(new DataImportThread(cyclicBarrier, "file1")).start();
		new Thread(new DataImportThread(cyclicBarrier, "file2")).start();
		new Thread(new DataImportThread(cyclicBarrier, "file3")).start();

	}
}