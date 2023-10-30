package com.ada.job.quartz.simple.listener;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

import com.ada.job.quartz.simple.MyFirstJob;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/2/7 14:43
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class MyJobListener implements JobListener {
	@Override
	public String getName() {
		String name = getClass().getSimpleName();
		System.out.println( "Method 111111 :"+ "获取到监听器名称："+name);
		return name;
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
		String jobName = jobExecutionContext.getJobDetail().getKey().getName();
		System.out.println("Method jobToBeExecuted :"+ jobName + " ——任务即将执行 ");
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
		String jobName = jobExecutionContext.getJobDetail().getKey().getName();
		System.out.println("Method jobExecutionVetoed :"+ jobName + " ——任务被否决 ");
	}

	@Override
	public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
		String jobName = jobExecutionContext.getJobDetail().getKey().getName();
		System.out.println("Method jobWasExecuted :"+ jobName + " ——执行完毕 ");
		System.out.println("------------------");
	}

	public static void main(String[] args) throws  Exception{
		// JobDetail
		JobDetail jobDetail = JobBuilder.newJob(MyFirstJob.class).withIdentity("job1", "group1").build();

		// Trigger
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

		// SchedulerFactory
		SchedulerFactory  factory = new StdSchedulerFactory();

		// Scheduler
		Scheduler scheduler = factory.getScheduler();

		scheduler.scheduleJob(jobDetail, trigger);

		// 创建并注册一个全局的Job Listener
		scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());

		scheduler.start();

	}
}
