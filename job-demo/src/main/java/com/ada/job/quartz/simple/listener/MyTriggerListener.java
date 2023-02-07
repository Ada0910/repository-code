package com.ada.job.quartz.simple.listener;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.KeyMatcher;

import com.ada.job.quartz.simple.MyFirstJob;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/2/7 15:03
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class MyTriggerListener implements TriggerListener {
	private String name;

	public MyTriggerListener(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	// Trigger 被触发，Job 上的 execute() 方法将要被执行时
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		String triggerName = trigger.getKey().getName();
		System.out.println("Method 11111 " + triggerName + " was fired");
	}

	// 在 Trigger 触发后，Job 将要被执行时由 Scheduler 调用这个方法
	// 返回true时，这个任务不会被触发
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		String triggerName = trigger.getKey().getName();
		System.out.println("Method 222222 " + triggerName + " was not vetoed");
		return false;
	}

	public void triggerMisfired(Trigger trigger) {
		String triggerName = trigger.getKey().getName();
		System.out.println("Method 333333 " + triggerName + " misfired");
	}

	public void triggerComplete(Trigger trigger, JobExecutionContext context,
	                            Trigger.CompletedExecutionInstruction triggerInstructionCode) {
		String triggerName = trigger.getKey().getName();
		System.out.println("Method 444444 " + triggerName + " is complete");
		System.out.println("------------");
	}

	public static void main(String[] args) throws  Exception{
// JobDetail
		JobDetail jobDetail = JobBuilder.newJob(MyFirstJob.class).withIdentity("job1", "group1").build();

		// Trigger
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();

		// SchedulerFactory
		SchedulerFactory  factory = new StdSchedulerFactory();

		// Scheduler
		Scheduler scheduler = factory.getScheduler();

		scheduler.scheduleJob(jobDetail, trigger);


		// 创建并注册一个全局的Trigger Listener
		scheduler.getListenerManager().addTriggerListener(new MyTriggerListener("myListener1"), EverythingMatcher.allTriggers());

		// 创建并注册一个局部的Trigger Listener
		scheduler.getListenerManager().addTriggerListener(new MyTriggerListener("myListener2"), KeyMatcher.keyEquals(TriggerKey.triggerKey("trigger1", "gourp1")));

		// 创建并注册一个特定组的Trigger Listener
		GroupMatcher<TriggerKey> matcher = GroupMatcher.triggerGroupEquals("gourp1");
		scheduler.getListenerManager().addTriggerListener(new MyTriggerListener("myListener3"), matcher);

		scheduler.start();
	}
}