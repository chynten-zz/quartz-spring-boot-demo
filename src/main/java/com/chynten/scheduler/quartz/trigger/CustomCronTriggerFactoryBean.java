package com.chynten.scheduler.quartz.trigger;

import org.joda.time.LocalDateTime;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by chintankumar.patel on 6/6/2016.
 */
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
