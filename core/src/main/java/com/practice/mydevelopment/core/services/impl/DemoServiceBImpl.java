package com.practice.mydevelopment.core.services.impl;

import com.day.cq.wcm.api.Page;
import com.practice.mydevelopment.core.services.DemoServiceA;
import com.practice.mydevelopment.core.services.DemoServiceB;
import com.practice.mydevelopment.core.services.MultiService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Component(service = DemoServiceB.class,immediate = true)
public class DemoServiceBImpl implements DemoServiceB {

    private static final Logger LOG = LoggerFactory.getLogger(DemoServiceBImpl.class);
    @Reference
    DemoServiceA demoServiceA;

    @Reference(target = "(component.name=com.practice.mydevelopment.core.services.impl.MultiServiceAImpl)")
    MultiService multiService;

    @Override
    public List<String> getPages() {
        List<String> listPages = new ArrayList<String>();

        try {
            Iterator<Page> pages = demoServiceA.getPages();
            while (pages.hasNext()) {
                listPages.add(pages.next().getTitle());
            }
            return listPages;
        } catch (Exception e) {
            LOG.info("\n  Exception occured mannn{} ", e.getMessage());
        }
        return null;
    }

    @Override
    public String getNameWithReference() {
        return multiService.getName();
    }
}
