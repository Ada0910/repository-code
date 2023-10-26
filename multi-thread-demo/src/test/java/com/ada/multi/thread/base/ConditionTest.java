package com.ada.multi.thread.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 测试类
 * <p/>
 * <b>Creation Time:</b> 2022/9/21 15:57
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class ConditionTest {
	public static void main(String[] args) {
		//重入锁
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		lock.newCondition();
		lock.newCondition();
		lock.newCondition();
		lock.newCondition();

		//step 2 -阻塞await
		new Thread(new ConditionWaitDemo(lock,condition)).start();
		//step 1
		//(condtion为空)
		new Thread(new ConditionNotifyDemo(lock,condition)).start();
	}
}
