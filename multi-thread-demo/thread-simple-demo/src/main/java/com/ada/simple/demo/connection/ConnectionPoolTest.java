package com.ada.simple.demo.connection;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 连接池的demo
 * <p/>
 *
 * @Date: 2023/9/20 23:39
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ConnectionPoolTest {
	static ConnectionPool pool = new ConnectionPool(10);
	static CountDownLatch start = new CountDownLatch(1);
	static CountDownLatch end;


	public static void main(String[] args) throws InterruptedException {
		int threadCount = 20;
		end = new CountDownLatch(threadCount);
		int count = 20;

		AtomicInteger got = new AtomicInteger();
		AtomicInteger notGot = new AtomicInteger();
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "connectionThread");
			thread.start();
		}
		start.countDown();
		end.await();
		System.out.println("调用的次数：" + (threadCount * count));
		System.out.println("got调用的次数：" + got);
		System.out.println("got调用的次数：" + notGot);
	}

	static class ConnectionRunner implements Runnable {
		int count;
		AtomicInteger got;
		AtomicInteger notGot;

		public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
			this.count = count;
			this.got = got;
			this.notGot = notGot;
		}

		@Override
		public void run() {
			try {
				start.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			while (count > 0) {
				try {
					Connection connection = pool.fetchConnection(1000);
					if (connection != null) {
						try {

							connection.createStatement();
							connection.commit();
						} finally {
							pool.releaseConnection(connection);
							got.incrementAndGet();
						}
					} else {
						notGot.incrementAndGet();
					}
				} catch (Exception e) {

				} finally {
					count--;
				}
			}
			end.countDown();
		}
	}

}
