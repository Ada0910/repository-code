package com.ada.multi.thread.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 重入锁的样例
 * <p/>
 *
 * @Date: 2022/9/19 22:39
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ReentrantLockDemo {
	static Lock lock = new ReentrantLock();
	//synchronized的原子操作改造成Lock

	public void demo() throws InterruptedException { //N线程来访问
		lock.lock(); //获得一个锁
		lock.unlock();// 释放锁
	}

	public static void main(String[] args) {

	}
}
