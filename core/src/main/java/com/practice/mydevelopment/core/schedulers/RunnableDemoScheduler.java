package com.practice.mydevelopment.core.schedulers;

import com.practice.mydevelopment.core.config.SchedulerConfiguration;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class,immediate = true)
@Designate(ocd= SchedulerConfiguration.class)
public class RunnableDemoScheduler implements Runnable{

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
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
        scheduleOptions.name(String.valueOf(schedulerId));
        scheduleOptions.canRunConcurrently(false);
        scheduler.schedule(this,scheduleOptions);
        log.info("\n-----------------------Scheduler added----------------");
        //To run the scheduler when we run the code irrespective of above cronExpression
        ScheduleOptions scheduleOptionsNow = scheduler.NOW();
        scheduler.schedule(this,scheduleOptionsNow);
        //To run the scheduler three times(i) for every five seconds(l) irrespective of above cronExpression
        ScheduleOptions scheduleOptionsThrice = scheduler.NOW(3,5);
        scheduler.schedule(this,scheduleOptionsThrice);
    }

    @Override
    public void run() {
        log.info("\n==============Run Method Executing==============");
    }
}
