package com.ada.job.quartz.simple;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

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
 * @since  1.0.0.1
 */
public class QuartzDemo implements Job {
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println("快要下班了！！！");
	}

	public static void main(String[] args) throws SchedulerException {
		// JobDetail
		JobDetail jobDetail = JobBuilder.newJob(QuartzDemo.class)
				.withIdentity("job1", "group1")
				.usingJobData("ada", "2673")
				.usingJobData("moon", 5.21F).build();

		// Trigger
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(2)
						.repeatForever())
				.build();


		// SchedulerFactory
		SchedulerFactory  factory = new StdSchedulerFactory();
		// Scheduler
		Scheduler scheduler = factory.getScheduler();

		// 绑定关系是1：N
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();

	}
}
