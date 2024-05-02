package com.ada.simple.demo.connection;


import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/9/20 23:10
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ConnectionDriver {
	public static Connection createConnection() {
		return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),new Class[]{Connection.class},new ConnectionHandler());
	}
}
