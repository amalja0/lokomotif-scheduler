package com.tujuhsembilan.scheduler.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.tujuhsembilan.scheduler.controller.LokomotifController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@DisallowConcurrentExecution
public class CreateDataJob implements Job, ApplicationContextAware {

    @Autowired
    private LokomotifController lokomotifController;

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) {
        lokomotifController = applicationContext.getBean(LokomotifController.class);
    }
    
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("Job ** {} ** starting @ {}", jobExecutionContext.getJobDetail().getKey().getName(), jobExecutionContext.getFireTime());
        lokomotifController.createLokomotifData();
        log.info("Job ** {} ** completed.  Next job scheduled @ {}", jobExecutionContext.getJobDetail().getKey().getName(),jobExecutionContext.getNextFireTime());
    }
    
}
