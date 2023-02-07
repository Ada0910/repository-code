package com.ada.job.quartz.simple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/2/7 11:34
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class MySecondJob implements Job {
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			System.out.println(" " + sf.format(date) + "   》》》》任务2开始执行了，需要15秒");
			TimeUnit.SECONDS.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

