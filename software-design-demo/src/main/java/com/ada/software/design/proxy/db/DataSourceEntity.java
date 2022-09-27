package com.ada.software.design.proxy.db;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 数据库选择实体类（ThreadLocal单例）
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 11:43
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class DataSourceEntity {
	public final static String DEFAULE_SOURCE = null;
	private final static ThreadLocal<String> local = new ThreadLocal<String>();

	//构造方法私有
	private DataSourceEntity() {
	}

	//全局访问点
	public static String get() {
		return local.get();
	}

	public static void set(String source) {local.set(source);}

	public static void restore() {
		local.set(DEFAULE_SOURCE);
	}

	public static void set(int year) {local.set("DB_" + year);}


}
