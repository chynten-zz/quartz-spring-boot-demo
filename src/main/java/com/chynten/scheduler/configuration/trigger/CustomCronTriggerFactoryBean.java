package com.chynten.scheduler.configuration.trigger;

import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import java.text.ParseException;
import java.util.Date;

public class CustomCronTriggerFactoryBean extends CronTriggerFactoryBean {

    private Date endTime;


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public void afterPropertiesSet() throws ParseException {
        super.afterPropertiesSet();


        if (super.getObject() != null) {
            CronTriggerImpl object = (CronTriggerImpl) super.getObject();
            object.setEndTime(endTime);
        }
    }
}
