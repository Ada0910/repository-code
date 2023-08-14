package com.ada.multi.thread.wait;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/14 23:28
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyListDemo {
	private static List list = new ArrayList();

	public static void add() {
		list.add("ada");
	}

	public static int size() {
		return list.size();
	}
}
