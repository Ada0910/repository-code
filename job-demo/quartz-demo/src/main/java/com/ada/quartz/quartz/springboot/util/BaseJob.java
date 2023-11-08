package com.ada.quartz.quartz.springboot.util;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface BaseJob extends Job {
	public void execute(JobExecutionContext context) throws JobExecutionException;
}

