package com.ada.multi.thread.keyword.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * 使用Condition实现顺序执行
 *
 * <p/>
 * <b>Creation Time:</b> 2023/8/29 11:28
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class ConditionOrderTest {
	private volatile static int nextPrintWho = 1;
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition conditionA = lock.newCondition();
	private static Condition conditionB = lock.newCondition();
	private static Condition conditionC = lock.newCondition();

	public static void main(String[] args) {
		Thread threadA = new Thread() {
			@Override
			public void run() {
				try {
					lock.lock();
					while (nextPrintWho != 1) {
						conditionA.await();
					}

					for (int i = 0; i < 3; i++) {
						System.out.println("线程A" + (i + 1));
					}
					nextPrintWho = 2;
					conditionB.signalAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}

			}
		};

		Thread threadB = new Thread() {
			@Override
			public void run() {
				try {
					lock.lock();
					while (nextPrintWho != 2) {
						conditionB.await();
					}

					for (int i = 0; i < 3; i++) {
						System.out.println("线程B" + (i + 1));
					}
					nextPrintWho = 3;
					conditionC.signalAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}

			}
		};

		Thread threadC = new Thread() {
			@Override
			public void run() {
				try {
					lock.lock();
					while (nextPrintWho != 3) {
						conditionC.await();
					}

					for (int i = 0; i < 3; i++) {
						System.out.println("线程C" + (i + 1));
					}   
					nextPrintWho = 1;
					conditionA.signalAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}

			}
		};
		
		Thread[] aArray = new Thread[5];
		Thread[] bArray = new Thread[5];
		Thread[] cArray = new Thread[5];
		for (int i = 0; i < 5; i++) {
			aArray[i] = new Thread(threadA);
			bArray[i] = new Thread(threadB);
			cArray[i] = new Thread(threadC);
			aArray[i].start();
			bArray[i].start();
			cArray[i].start();
		}
	}
}
