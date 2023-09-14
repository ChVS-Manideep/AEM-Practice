package com.practice.mydevelopment.core.services.impl;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.practice.mydevelopment.core.services.DemoServiceA;
import com.practice.mydevelopment.core.utils.ResolverUtil;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

@Component(service = DemoServiceA.class, immediate = true)
public class DemoServiceAImpl implements DemoServiceA {

    private static final Logger LOG = LoggerFactory.getLogger(DemoServiceAImpl.class);

    @Reference
    private SlingRepository slingRepository;

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Activate
    public void activate(ComponentContext componentContext){
        LOG.info("\n================INSIDE ACTIVATE=============");
        LOG.info("\n {} = {}",componentContext.getBundleContext().getBundle().getBundleId(),componentContext.getBundleContext().getBundle().getSymbolicName());
    }

    @Deactivate
    public void deactivate(){
        LOG.info("=============INSIDE DEACTIVATE===============");
    }

    @Modified
    public void modified(){
        LOG.info("=============INSIDE MODIFIED===============");
    }

    @Override
    public Iterator<Page> getPages() {
        try{
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage("/content/mydevelopment/language-masters/en");
            Iterator<Page> pages = page.listChildren();
            return pages;
        }catch (LoginException e){
            LOG.info("\n Login Exception : {}",e.getMessage());
        }
        return null;
    }
}
