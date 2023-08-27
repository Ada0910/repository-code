package com.ada.multi.thread.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/27 13:03
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyService1 extends  MyService{

	private Lock lock = new ReentrantLock();
	private Condition conditionA = lock.newCondition();
	private Condition conditionB = lock.newCondition();
	public void awaitA() {

		try {
			lock.lock();
			System.out.println("线程："+ Thread.currentThread().getName() + ",执行awaitA的方法，开始的时间为" +  System.currentTimeMillis() );
			conditionA.await();
			System.out.println("线程："+ Thread.currentThread().getName() + ",执行awaitA的方法，执行完的时间为" +  System.currentTimeMillis() );
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void awaitB() {

		try {
			lock.lock();
			System.out.println("线程："+ Thread.currentThread().getName() + ",执行awaitB的方法，开始的时间为" +  System.currentTimeMillis() );
			conditionB.await();
			System.out.println("线程："+ Thread.currentThread().getName() + ",执行awaitB的方法，执行完的时间为" +  System.currentTimeMillis() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void signalA(){
		try {
			lock.lock();
			System.out.println("线程："+ Thread.currentThread().getName() + ",执行signalA，开始的时间为" +  System.currentTimeMillis() );
			conditionA.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public void signalB(){
		try {
			lock.lock();
			System.out.println("线程："+ Thread.currentThread().getName() + ",执行signalB，开始的时间为" +  System.currentTimeMillis() );
			conditionB.signalAll();
		} finally {
			lock.unlock();
		}
	}
}
