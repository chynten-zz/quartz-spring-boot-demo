package com.chynten.scheduler.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("HelloWorld!");
    }
}
