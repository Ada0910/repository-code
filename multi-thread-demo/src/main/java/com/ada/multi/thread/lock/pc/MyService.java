package com.ada.multi.thread.lock.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/27 17:06
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyService {
	private ReentrantLock lock = new ReentrantLock();

	private Condition condition = lock.newCondition();

	private boolean hasValue = false;

	public void set() {
		try {
			lock.lock();
			while (hasValue== true) {
				System.out.println("有可能连续打印★★★★★");
				condition.await();
			}
			System.out.println("★★★★★");
			hasValue = true;
			condition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}


	public void get(){
		try {
			lock.lock();
			while (hasValue==false){
				System.out.println("有可能连续打印☆☆☆☆☆");
				condition.await();
			}

			System.out.println("☆☆☆☆☆");
			hasValue = false;
			condition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}


}
