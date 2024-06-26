package com.ada.simple.aqs.reentrantlock.customize;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 自定义同步组件
 * <p/>
 * <b>Creation Time:</b> 2023/9/21 10:39
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class TwinsLock implements Lock {
	private final Sync sync = new Sync(2);

	private static final class Sync extends AbstractQueuedSynchronizer {
		Sync(int count) {
			if (count <= 0) {
				throw new IllegalArgumentException("count must large than zero.");
			}
			setState(count);
		}

		public int tryAcquireShared(int reduceCount) {
			for (; ; ) {
				int current = getState();
				int newCount = current - reduceCount;
				if (newCount < 0 || compareAndSetState(current,
						newCount)) {
					return newCount;
				}
			}
		}

		public boolean tryReleaseShared(int returnCount) {
			for (; ; ) {
				int current = getState();
				int newCount = current + returnCount;
				if (compareAndSetState(current, newCount)) {
					return true;
				}
			}
		}
	}

	public void lock() {
		sync.acquireShared(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

	public void unlock() {
		sync.releaseShared(1);
	}

	@Override
	public Condition newCondition() {
		return null;
	}
// 其他接口方法略
}
