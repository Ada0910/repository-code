package com.ada.job.quartz.spring;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/2/7 15:22
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class MyFirstJob implements Job {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.info("窝窝头一块钱四个：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}
