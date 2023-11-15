package com.tujuhsembilan.scheduler.configuration;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.tujuhsembilan.scheduler.job.CreateDataJob;

@Configuration
public class QuartzConfig {
    
    // Job
    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(CreateDataJob.class);
        factory.setDurability(true);
        return factory;
    }

    // Trigger
    @Bean
    public CronTriggerFactoryBean trigger(JobDetail job) {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(job);
        factory.setCronExpression("0/10 * * * * ?"); // Every 10 seconds
        factory.setName("randomDataTrigger");
        factory.setGroup("randomDataGroup");
        return factory;
    }

    // Scheduler
    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job, SpringBeanJobFactory springBeanJobFactory) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobDetails(job);
        factory.setTriggers(trigger);
        factory.setJobFactory(springBeanJobFactory);
        return factory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        return new SpringBeanJobFactory();
    }

}
