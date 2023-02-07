package com.ada.job.quartz.spring;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/2/7 15:24
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class QuartzTest {
	private static Scheduler scheduler;

	public static void main(String[] args) throws SchedulerException {
		// 获取容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring_quartz.xml");

		// 从容器中获取调度器
		scheduler = (StdScheduler) ac.getBean("scheduler");

		// 启动调度器
		scheduler.start();
	}
}
