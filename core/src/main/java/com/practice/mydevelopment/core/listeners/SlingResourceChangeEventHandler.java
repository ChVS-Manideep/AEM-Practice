package com.practice.mydevelopment.core.listeners;

import com.practice.mydevelopment.core.utils.ResolverUtil;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import java.util.ArrayList;
import java.util.List;

@Component(service = ResourceChangeListener.class,
           immediate = true,
           property = {
                ResourceChangeListener.PATHS+"=/content/mydevelopment/language-masters/en/test-page",
                ResourceChangeListener.CHANGES+"=ADDED",
                ResourceChangeListener.CHANGES+"=REMOVED",
                ResourceChangeListener.CHANGES+"=CHANGED"
           }
)
public class SlingResourceChangeEventHandler implements ResourceChangeListener{
    private static final Logger LOGGER = LoggerFactory.getLogger(SlingResourceChangeEventHandler.class);

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Override
    public void onChange(List<ResourceChange> list) {
        for(ResourceChange rc : list) {
            try {
                LOGGER.info("\n Event : {} , Resource : {}", rc.getType(), rc.getPath());
                ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
                Resource resource = resourceResolver.getResource(rc.getPath());
                Node node = resource.adaptTo(Node.class);
                node.setProperty("eventHandlerTask","Event "+rc.getType()+" by "+resourceResolver.getUserID());
                resourceResolver.commit();
            }catch (Exception e){
                LOGGER.info("\n Error Occured {}",e.getMessage());
            }
        }
    }
}
