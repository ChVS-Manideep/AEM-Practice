package com.practice.mydevelopment.core.listeners;

import com.day.cq.wcm.api.PageEvent;
import com.day.cq.wcm.api.PageModification;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

@Component(service = EventHandler.class,
immediate = true,
property = {
        EventConstants.EVENT_TOPIC+"="+ PageEvent.EVENT_TOPIC
})
public class PageActionEventHandler implements EventHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplicationEventHandler.class);

    @Override
    public void handleEvent(Event event) {
        try {
            Iterator<PageModification> pageInfo = PageEvent.fromEvent(event).getModifications();
            while (pageInfo.hasNext()){
                PageModification pageModification = pageInfo.next();
                LOGGER.info("\n Type : {} , Page : {} ",pageModification.getType(),pageModification.getPath());
                pageModification.getEventProperties().forEach((k,v)->LOGGER.info("\n Key : {} , Value : {}",k,v));
            }
        }catch (Exception e){
            LOGGER.info("\n Error Occured : {} ",e.getMessage());
        }
    }
}
