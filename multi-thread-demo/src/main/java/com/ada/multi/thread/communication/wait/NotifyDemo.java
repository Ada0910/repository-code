package com.ada.multi.thread.communication.wait;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 唤醒样例
 * <p/>
 *
 * @Date: 2023/8/14 23:09
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class NotifyDemo extends Thread {
	private Object lock;

	public NotifyDemo(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				System.out.println("notify开始执行同步块！");
				for (int i = 0; i < 10; i++) {
					MyListDemo.add();
					if (MyListDemo.size() == 5) {
						lock.notify();
						System.out.println("已发出通知！");
					}

					System.out.println("已添加了 " + (i+1)+ "个元素！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("notify执行同步块完毕！");
	}
}

