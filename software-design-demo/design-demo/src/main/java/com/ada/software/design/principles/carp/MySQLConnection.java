package com.ada.software.design.principles.carp;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/1 17:47
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class MySQLConnection extends DBConnection{
	@Override
	public String getConnection() {
		return "MySQL 数据库连接";
	}
}
