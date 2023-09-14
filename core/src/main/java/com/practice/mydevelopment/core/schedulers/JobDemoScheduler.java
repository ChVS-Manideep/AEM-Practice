package com.practice.mydevelopment.core.schedulers;

import com.practice.mydevelopment.core.config.SchedulerConfiguration;
import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component(service = Job.class,immediate = true)
@Designate(ocd= SchedulerConfiguration.class)
public class JobDemoScheduler implements Job{
    private static final Logger log = LoggerFactory.getLogger(RunnableDemoScheduler.class);

    private int schedulerId;

    @Reference
    private Scheduler scheduler;

    @Activate
    protected void activate(SchedulerConfiguration config){
        schedulerId = config.schedulerName().hashCode();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate(SchedulerConfiguration config){
        removeScheduler();
    }

    private void removeScheduler(){
        scheduler.unschedule(String.valueOf(schedulerId));
    }

    private void addScheduler(SchedulerConfiguration config){
        ScheduleOptions in = scheduler.EXPR("0 22 22 1/1 * ? *");
        Map<String, Serializable> inMap = new HashMap<>();
        inMap.put("country","IN");
        inMap.put("url","www.in.com");
        in.config(inMap);
        scheduler.schedule(this,in);
        ScheduleOptions us = scheduler.EXPR("0 23 22 1/1 * ? *");
        Map<String, Serializable> usMap = new HashMap<>();
        usMap.put("country","US");
        usMap.put("url","www.us.com");
        us.config(usMap);
        scheduler.schedule(this,us);
        ScheduleOptions de = scheduler.EXPR("0 24 22 1/1 * ? *");
        Map<String, Serializable> deMap = new HashMap<>();
        deMap.put("country", "DE");
        deMap.put("url", "www.DE.com");
        de.config(deMap);
        scheduler.schedule(this, de);
    }

    @Override
    public void execute(JobContext jobContext) {
        log.info("\n=======================> COUNTRY :{} URL : {} ",jobContext.getConfiguration().get("country"),jobContext.getConfiguration().get("url"));
    }
}
