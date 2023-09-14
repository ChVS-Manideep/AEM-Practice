package com.practice.mydevelopment.core.listeners;

import com.practice.mydevelopment.core.utils.ResolverUtil;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = JobConsumer.class,immediate = true,
property = {
        JobConsumer.PROPERTY_TOPICS+"=mydev/job"
})
public class JobConsumerEventHandler implements JobConsumer{

    private static final Logger LOGGER = LoggerFactory.getLogger(JobCreatorEventHandler.class);

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Override
    public JobResult process(Job job) {
        try {
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            String path = (String) job.getProperty("path");
            String event = (String) job.getProperty("event");
            String heropage = (String) job.getProperty("heropage");
            LOGGER.info("\n Job executing for : {} ",resourceResolver.getResource(heropage).getName());
            return JobResult.OK;
        } catch (Exception e) {
            LOGGER.info("\n Error occured : {}",e.getMessage());
            return JobResult.FAILED;
        }

    }
}
