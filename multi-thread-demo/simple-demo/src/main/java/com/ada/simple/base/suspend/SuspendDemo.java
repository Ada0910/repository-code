package com.ada.multi.thread.base.suspend;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 暂停线程demo，suspend能够独占且锁死方法
 *
 *
 * 缺点： 独占
 * <p/>
 *
 * @Date: 2023/8/5 18:48
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SuspendDemo {

	synchronized public void printString() {
		System.out.println("Begin");
		if (Thread.currentThread().getName().equals("a")) {
			System.out.println(Thread.currentThread().getName() + "线程永远暂停了！");
			Thread.currentThread().suspend();
		}
		System.out.println("End");
	}

	public static void main(String[] args) {
		try {
			SuspendDemo demo = new SuspendDemo();
			Thread thread = new Thread() {
				@Override
				public void run() {
					demo.printString();
				}
			};

			thread.setName("a");
			thread.start();
			Thread.sleep(1000);

			Thread thread2 = new Thread() {
				@Override
				public void run() {
					System.out.println("线程2启动了，但是进不了printString方法");
					demo.printString();
				}
			};

			thread2.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
