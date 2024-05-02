package com.ada.simple.status.interrupt;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/8/29 9:55
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class LockInterruptiblyService {

	public ReentrantLock lock = new ReentrantLock();

	public Condition condition = lock.newCondition();

	public void waitMethod() {
		try {
			//lock.lock();
			lock.lockInterruptibly();
			System.out.println("线程" + Thread.currentThread().getName() + "开始执行！");
			for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {
				String newString = new String();
				Math.random();
			}
			System.out.println("线程" + Thread.currentThread().getName() + "执行完毕！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}
	
	public void tryLock(){
		if(lock.tryLock()){
			System.out.println("线程：" + Thread.currentThread().getName() +"获得锁！");
		}else{
			System.out.println("线程：" + Thread.currentThread().getName() +"没有获得锁！");
		}
	}
}
