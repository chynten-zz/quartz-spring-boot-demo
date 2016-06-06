package com.chynten.scheduler.configuration;

import com.chynten.scheduler.quartz.job.TestJob;
import com.chynten.scheduler.quartz.trigger.CustomCronTriggerFactoryBean;
import org.joda.time.LocalDateTime;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

@Configuration
public class QuartzSchedulerConfig {

    @Autowired
    DataSource dataSource;

    @Autowired
    private ApplicationContext context;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);

        factory.setQuartzProperties(quartzProperties());
        factory.setTriggers(sampleJobTrigger());

        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    public JobDetail sampleJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();

        factoryBean.setJobClass(TestJob.class);
        factoryBean.setDurability(true);
        factoryBean.setApplicationContext(context);
        factoryBean.setName("test-job");

        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    public Trigger sampleJobTrigger() {
        /*SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(sampleJobDetail());
        factoryBean.setStartDelay(0L);
        factoryBean.setRepeatInterval(5000);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        // in case of misfire, ignore all missed triggers and continue :
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
        factoryBean.setName("test-trigger");
        factoryBean.afterPropertiesSet();*/

        System.out.println("wait for 5 min from: " + new Date());

        CustomCronTriggerFactoryBean factoryBean = new CustomCronTriggerFactoryBean();
        factoryBean.setJobDetail(sampleJobDetail());
        factoryBean.setName("test-trigger");
        factoryBean.setStartTime(LocalDateTime.now().plusMinutes(2).toDate());
        factoryBean.setEndTime(LocalDateTime.now().plusMinutes(5).toDate());
        factoryBean.setCronExpression("*/30 * * ? * *");
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT);

        try {
            factoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CronTrigger object = factoryBean.getObject();

        return object;
    }

}
