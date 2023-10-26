package com.ada.multi.thread.keyword.volati.demo;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 不具备原子性
 * <p/>
 * <b>Creation Time:</b> 2023/8/14 16:59
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class MyThread extends Thread {
	public volatile static int count;

	@Override
	public void run() {
		addCount();
	}

	// 加上synchronized就可以确保同步性
	synchronized  private static void addCount() {
		for (int i = 0; i < 100; i++) {
			count++;
		}
		System.out.println("线程名字："+Thread.currentThread().getName() +",I = " + count);
	}

	public static void main(String[] args) {
		MyThread[] myThreads = new MyThread[100];
		for (int i = 0; i < 100; i++) {
			myThreads[i] = new MyThread();
		}
		for (int i = 0; i < 100; i++) {
			myThreads[i].start();
		}


	}
}
