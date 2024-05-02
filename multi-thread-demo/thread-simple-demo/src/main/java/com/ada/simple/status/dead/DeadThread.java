package com.ada.simple.status.dead;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 模拟多线程死锁
 * <p/>
 *
 * @Date: 2023/8/13 16:32
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class DeadThread implements Runnable {
	public String userName;
	public Object lock1 = new Object();
	public Object lock2 = new Object();

	public void setFlag(String userName) {
		this.userName = userName;
	}

	@Override
	public void run() {
		if ("a".equals(userName)) {
			synchronized (lock1) {
				System.out.println("userName : " + userName);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock2) {
					System.out.println("lock1-> lock2执行了");
				}
			}

		} else if ("b".equals(userName)) {
			synchronized (lock2) {
				System.out.println("userName : " + userName);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock1) {
					System.out.println("lock2-> lock1执行了");
				}
			}

		}
	}

	public static void main(String[] args) throws InterruptedException {
		DeadThread t1 = new DeadThread();
		t1.setFlag("a");
		Thread threadA = new Thread(t1);
		threadA.start();
		Thread.sleep(100);
		t1.setFlag("b");
		Thread threadB = new Thread(t1);
		threadB.start();

	}
}
