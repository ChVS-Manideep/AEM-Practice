package com.practice.mydevelopment.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "My Dev - Scheduler Config",
        description = "This config is used for scheduling")
public @interface SchedulerConfiguration {

    @AttributeDefinition(
            name = "Scheduler Name",
            description = "Enter the name of the scheduler",
            type = AttributeType.STRING
    )
   public String schedulerName() default "Custom Sling Scheduler Configuration";

    @AttributeDefinition(
            name = "Cron Expression",
            description = "Cron Expression by the scheduler",
            type = AttributeType.STRING
    )
    public String cronExpression() default "0/20 * * * * ?";
}
