package com.ada.simple.base.group.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/30 23:29
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyThread extends Thread {
	private SimpleDateFormat format;
	private String dateString;
	private static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<>();

	public MyThread(SimpleDateFormat format, String dateString) {
		this.format = format;
		this.dateString = dateString;
	}

	@Override
	public void run() {
		//errorFormat();
		rightFormat();
	}

	private void rightFormat() {
		try {
			Date date = getSimpleDateFormat("yyyy-MM-dd").parse(dateString);
			String newDateString = getSimpleDateFormat("yyyy-MM-dd").format(date).toString();
			System.out.println("线程名字：" + this.getName() + " 日期字符串：" + dateString + "转换后的日期为：" + newDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private static SimpleDateFormat getSimpleDateFormat(String pattern) {
		SimpleDateFormat simpleDateFormat = null;
		simpleDateFormat = t1.get();
		if (simpleDateFormat == null) {
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			t1.set(simpleDateFormat);
		}
		return simpleDateFormat;
	}

	private void errorFormat() {
		try {
			Date date = format.parse(dateString);
			String newDateString = format.format(date).toString();
			if (!newDateString.equals(dateString)) {
				System.out.println("线程名字：" + this.getName() + "报错了， 日期字符串：" + dateString + "转换后的日期为：" + newDateString);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
