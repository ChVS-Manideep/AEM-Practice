package com.practice.mydevelopment.core.listeners;


import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationEvent;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.PageEvent;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = EventHandler.class,
immediate = true,
property = {
        EventConstants.EVENT_TOPIC+"="+ ReplicationEvent.EVENT_TOPIC
})
public class ReplicationEventHandler implements EventHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplicationEventHandler.class);


    @Override
    public void handleEvent(Event event) {
        try{
            LOGGER.info("\n Event Type : {} ",event.getTopic());
            if(ReplicationAction.fromEvent(event).getType().equals(ReplicationActionType.ACTIVATE)){
                LOGGER.info("\n Page  Published : {} ",ReplicationAction.fromEvent(event).getPath());
            }
            if(ReplicationAction.fromEvent(event).getType().equals(ReplicationActionType.DEACTIVATE)){
                LOGGER.info("\n Page Deactivated : {} ",ReplicationAction.fromEvent(event).getPath());
            }
        }catch (Exception e){
            LOGGER.info("\n Error Occured : {} ",e.getMessage());
        }
    }
}
