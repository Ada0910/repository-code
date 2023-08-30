package com.ada.multi.thread.group.format;

import java.text.SimpleDateFormat;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/30 23:33
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Test {
	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String[] dateArray = new String[]{"2023-08-01", "2023-08-02", "2023-08-03", "2023-08-04", "2023-08-05", "2023-08-06","2023-08-07"};
		MyThread[] threads = new MyThread[7];
		for (int i = 0; i < 7; i++) {
			threads[i] = new MyThread(simpleDateFormat, dateArray[i]);
		}

		for (int i = 0; i < 7; i++) {
			threads[i].start();
		}
	}
}
