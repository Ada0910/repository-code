package com.ada.job.quartz.spring;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
@Configuration
public class QuartzConfig {
	@Bean
	public JobDetail printTimeJobDetail() {
		return JobBuilder.newJob(MyFirstJob.class)
				.withIdentity("job")
				.usingJobData("ada", "热爱")
				.storeDurably()
				.build();
	}

	@Bean
	public Trigger printTimeJobTrigger() {
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
		return TriggerBuilder.newTrigger()
				.forJob(printTimeJobDetail())
				.withIdentity("quartzTaskService")
				.withSchedule(cronScheduleBuilder)
				.build();
	}
}
