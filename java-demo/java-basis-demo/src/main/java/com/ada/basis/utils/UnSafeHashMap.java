package com.ada.basis.utils;

import java.util.HashMap;
import java.util.UUID;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 不安全的hashmap 
 * <p/>
 * <b>Creation Time:</b> 2023/10/26 10:11
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class UnSafeHashMap {
	public static void main(String[] args) throws  Exception{
		final HashMap<String, String> map = new HashMap<String, String>(2);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							map.put(UUID.randomUUID().toString(), "");
						}
					}, "ftf" + i).start();
				}
			}
		}, "ftf");
		t.start();
		t.join();
	}
}
