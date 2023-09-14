package com.practice.mydevelopment.core.listeners;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

@Component(service = EventListener.class,immediate = true)
public class JCREventHandler implements EventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(JCREventHandler.class);
    private Session session;
    @Reference
    SlingRepository slingRepository;

    @Activate
    public void activate(){
        try{
            String[] nodeTypes = {"cq:PageContent"};
            session = slingRepository.loginService("mydevserviceuser",null);
            session.getWorkspace().getObservationManager().addEventListener(
                    this,
                    Event.NODE_ADDED | Event.PROPERTY_ADDED,
                    "/content/mydevelopment/language-masters/en/en",
                    true,
                    null,
                    nodeTypes,
                    true
            );
        }catch (RepositoryException e){
            LOGGER.info("\n Error Occured : {} ",e.getMessage());
        }
    }
    @Override
    public void onEvent(EventIterator eventIterator) {
        try{
            while (eventIterator.hasNext()){
                LOGGER.info("\n Type : {} ||| Path : {} ",eventIterator.nextEvent().getType(),eventIterator.nextEvent().getPath());
            }
        }catch (RepositoryException e){
            LOGGER.info("\n Error : {} ",e.getMessage());
        }
    }
}
