package com.ada.multi.thread.interrupt;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 在沉睡中停止
 * <p/>
 *
 * @Date: 2023/8/5 17:27
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SleepInterruptThreadDemo extends Thread {

	@Override
	public void run() {
		try {
			System.out.println("Begin");
			Thread.sleep(10000);
			System.out.println("end");
		} catch (InterruptedException e) {
			//isInterrupted不会清除中断标志，也就是说，不会恢复成false
			System.out.println("在沉睡中停止异常！isInterrupted:" +currentThread().getName()+":"+ this.isInterrupted());
			e.printStackTrace();
		}

	}

	//主线程
	public static void main(String[] args) {
		SleepInterruptThreadDemo thread = new SleepInterruptThreadDemo();
		thread.start();
		try {
			System.out.println("当前的线程中断标志是：" + currentThread().getName()+ ":"+ currentThread().isInterrupted());
			Thread.sleep(200);
			thread.interrupt();
			System.out.println("当前的线程中断标志是：" + currentThread().getName()+ ":"+ currentThread().isInterrupted());
		} catch (InterruptedException e) {
			System.out.println("主线程捕获异常！");
			e.printStackTrace();
		}

		System.out.println("主线程结束！");
	}
}
