package com.ada.simple.demo.connection;

import java.sql.Connection;
import java.util.LinkedList;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/9/20 23:08
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ConnectionPool {
	private LinkedList<Connection> pool = new LinkedList<>();

	public ConnectionPool(int initalSize) {
		if (initalSize > 0) {
			for (int i = 0; i < initalSize; i++) {
				pool.addLast(ConnectionDriver.createConnection());
			}
		}
	}


	public void releaseConnection(Connection connection) {
		if (connection != null) {
			synchronized (pool) {
				pool.addLast(connection);
				pool.notifyAll();
			}
		}
	}

	public Connection fetchConnection(long mills) throws InterruptedException {
		synchronized (pool) {
			if (mills < 0) {
				while (pool.isEmpty()) {
					pool.wait();
				}
				return pool.removeLast();
			} else {
				long future = System.currentTimeMillis() + mills;
				long remaining = mills;
				while (pool.isEmpty() && remaining > 0) {
					pool.wait(remaining);
					remaining = future - System.currentTimeMillis();
				}
				Connection result = null;
				if (!pool.isEmpty()) {
					result = pool.removeFirst();
				}
				return result;
			}

		}
	}
}
