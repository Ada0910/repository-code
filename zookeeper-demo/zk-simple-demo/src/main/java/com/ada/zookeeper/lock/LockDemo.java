package com.ada.zookeeper.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * curator分布式锁的基本使用 demo
 *
 * curator 对于锁这块做了一些封装，curator 提供了
 * InterProcessMutex 这样一个 api。除了分布式锁之外，
 * 还提供了 leader 选举、分布式队列等常用的功能
 *
 * <p/>
 *
 * @Date: 2022/12/11 16:31
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class LockDemo {
	private static String CONNECTION_STR = "127.0.0.1:2181";

	public static void main(String[] args) {
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTION_STR).sessionTimeoutMs(50000000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		curatorFramework.start();

		/**
		 *
		 * InterProcessMutex：分布式可重入排它锁
		 * InterProcessSemaphoreMutex：分布式排它锁
		 * InterProcessReadWriteLock：分布式读写锁
		 */
		final InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/locks");
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "->尝试竞争锁");
				try {
					lock.acquire(); //阻塞竞争锁
					System.out.println(Thread.currentThread().getName() + "->成功获得了锁");
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					try {
						lock.release(); //释放锁
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

					, "Thread-" + i).start();
		}

	}
}
