package com.ada.simple.base.threadlocal.simple;

import java.util.Date;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/23 23:40
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadLocalTools {
	public static ThreadLocal t1 = new ThreadLocal();

	public static ThreadLocal<Date> t2 = new ThreadLocal<>();
}
