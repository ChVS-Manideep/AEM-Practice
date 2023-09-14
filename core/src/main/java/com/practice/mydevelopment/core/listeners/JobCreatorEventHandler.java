package com.practice.mydevelopment.core.listeners;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(service = EventHandler.class,
            immediate = true,
            property = {
                    EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/ADDED",
                    EventConstants.EVENT_FILTER+"=(path=/content/mydevelopment/language-masters/en/authoring/*)"
            })
public class JobCreatorEventHandler implements EventHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(JobCreatorEventHandler.class);

    @Reference
    JobManager jobManager;

    @Override
    public void handleEvent(Event event) {
        try{
            Map<String,Object> jobProperties = new HashMap<>();
            jobProperties.put("event", event.getTopic());
            jobProperties.put("path",event.getProperty(SlingConstants.PROPERTY_PATH));
            jobProperties.put("heropage","heroPage");
            Job job = jobManager.addJob("mydev/job",jobProperties);
        }catch (Exception e){
            LOGGER.info("\n Exception is {} ",e.getMessage());
        }
    }
}
