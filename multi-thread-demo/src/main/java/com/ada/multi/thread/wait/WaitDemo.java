package com.ada.multi.thread.wait;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * waitDemo
 * <p/>
 *
 * @Date: 2023/8/14 23:00
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class WaitDemo extends Thread {
	private Object lock;

	public WaitDemo(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		synchronized (lock) {
			if (MyListDemo.size() != 5) {
				System.out.println("wait开始执行同步块！");
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("wait执行同步块完毕！");
			}
		}
	}

	public static void main(String[] args) {
		addSynTest();
	}

	/**
	 * 以下代码会报：Exception in thread "main" java.lang.IllegalMonitorStateException
	 * 出现异常的原因就是：没有同步加锁
	 */
	public void test1() {
		String newString = new String("");
		try {
			newString.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加锁
	 */
	public static void addSynTest() {
		try {
			String newString = new String("");
			System.out.println("在进入到同步代码块之前！");
			synchronized (newString) {
				System.out.println("进入同步方法的第一行！");
				newString.wait();
				System.out.println("执行同步代码");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
