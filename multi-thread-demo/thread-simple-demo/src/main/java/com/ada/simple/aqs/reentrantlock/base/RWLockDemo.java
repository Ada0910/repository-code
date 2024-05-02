package com.ada.simple.aqs.reentrantlock.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 写锁demo
 * <p/>
 *
 * @Date: 2022/9/19 22:52
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RWLockDemo {
	static ReentrantReadWriteLock wrl = new ReentrantReadWriteLock();

	static Map<String, Object> cacheMap = new HashMap<>();

	static Lock read = wrl.readLock();
	static Lock write = wrl.writeLock();

	//线程B/C/D
	public static final Object get(String key) {
		System.out.println("begin read data:" + key);
		read.lock(); //获得读锁-> 阻塞
		try {
			return cacheMap.get(key);
		} finally {
			read.unlock();
		}
	}

	//线程A
	public static final Object put(String key, Object val) {
		write.lock();//获得了写锁
		try {
			return cacheMap.put(key, val);
		} finally {
			write.unlock();
		}
	}


	public static void main(String[] args) {
		wrl.readLock();//B线程 ->阻塞

		wrl.writeLock(); //A线程

		//读->读是可以共享
		//读->写 互斥
		//写->写 互斥
		//读多写少的场景
	}
}
