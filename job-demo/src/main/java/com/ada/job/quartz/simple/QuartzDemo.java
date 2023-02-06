package com.ada.job.quartz.simple;

import org.quartz.*;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * Quartz定时任务简单样例
 *
 * <p/>
 * <b>Creation Time:</b> 2023/2/6 15:49
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class QuartzDemo implements Job {
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println("快要下班了！！！");
	}

	public static void main(String[] args) {
		JobDetail jobDetail = JobBuilder.newJob(QuartzDemo.class)
				.withIdentity("job1", "group1")
				.usingJobData("ada", "2673")
				.usingJobData("moon", 5.21F).build();
	}
}
