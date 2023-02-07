package com.ada.job.quartz.simple;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/2/7 11:33
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class MyFirstJob  implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		System.out.println( " " + sf.format(date) + " 任务1执行了，" + dataMap.getString("ada"));

	}
}
