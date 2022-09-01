package com.ada.software.design.principles.carp;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/1 17:46
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class ProductDao {
	private DBConnection dbConnection;

	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public void addProduct() {
		String conn = dbConnection.getConnection();
		System.out.println("使用" + conn + "增加产品");
	}
}
