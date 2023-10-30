package com.ada.job.quartz.simple.jdk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * <b><code></code></b>
 * <p/>
 * JDK自带的定时任务器
 * <p/>
 * <b>Creation Time:</b> 2023/2/7 10:57
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class JdkTimerTask  extends TimerTask {
	@Override
	public void run() {
		Date executeTime = new Date(this.scheduledExecutionTime());
		String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println("任务执行了：" + dateStr);
	}

	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask task = new JdkTimerTask();
		timer.schedule(task, 5000L, 1000L);
	}
}
