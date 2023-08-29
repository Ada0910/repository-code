package com.ada.multi.thread.lock.utils;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 
 * lockInterruptibly 当前线程未被中断，则获得锁定，如果已经被中断则抛出异常
 * 
 * <p/>
 * <b>Creation Time:</b> 2023/8/29 9:56
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class LockInterruptiblyTest {
	public static void main(String[] args) throws InterruptedException {
		LockInterruptiblyService service = new LockInterruptiblyService();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				//service.waitMethod();
				service.tryLock();
			}
		};
		
		Thread threadA = new Thread(runnable);
		threadA.setName("A");
		threadA.start();
		Thread.sleep(1000);

		Thread threadB = new Thread(runnable);
		threadB.setName("B");
		threadB.start();
		//threadB.interrupt();
		System.out.println("主线程执行完毕！");
	}
}
