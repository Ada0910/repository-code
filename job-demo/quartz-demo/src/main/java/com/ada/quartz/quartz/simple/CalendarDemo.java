package com.ada.quartz.quartz.simple;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * 基于 r Calendar  的 排除规则
 *
 * 如果要在触发器的基础上，排除一些时间区间不执行任务，就要用到 Quartz 的
 * Calendar 类（注意不是 JDK 的 Calendar）。可以按年、月、周、日、特定日期、Cron
 * 表达式排除
 *
 * <p/>
 *
 * @Date: 2023/2/7 0:05
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class CalendarDemo {
	public static void main(String[] args) throws Exception {

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		scheduler.start();

		/*
		 * 定义日历
		 * AnnualCalendar排除年中一天或多天
		 */
		AnnualCalendar holidays = new AnnualCalendar();

		// 排除咕泡日
		Calendar gupaoDay = new GregorianCalendar(2019, 8, 8);
		holidays.setDayExcluded(gupaoDay, true);
		// 排除中秋节
		Calendar midAutumn = new GregorianCalendar(2019, 9, 13);
		holidays.setDayExcluded(midAutumn, true);
		// 排除圣诞节
		Calendar christmas = new GregorianCalendar(2019, 12, 25);
		holidays.setDayExcluded(christmas, true);

		// 调度器添加日历
		scheduler.addCalendar("holidays", holidays, false, false);

		JobDetail jobDetail = JobBuilder.newJob(QuartzDemo.class).withIdentity("job1", "group1").usingJobData("gupao", "青山 2673").build();

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow().modifiedByCalendar("holidays").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

		Date firstRunTime = scheduler.scheduleJob(jobDetail, trigger);
		System.out.println(jobDetail.getKey() + " 第一次触发： " + firstRunTime);
	}
}
