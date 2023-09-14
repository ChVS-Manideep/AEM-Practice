package com.practice.mydevelopment.core.models;

import com.day.cq.wcm.api.Page;
import com.practice.mydevelopment.core.services.DemoServiceA;
import com.practice.mydevelopment.core.services.DemoServiceB;
import com.practice.mydevelopment.core.services.MultiService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceDemoModel {
    @OSGiService
    DemoServiceA demoServiceA;

    @OSGiService
    DemoServiceB demoServiceB;

//    @OSGiService(filter = "(component.name=com.practice.mydevelopment.core.services.impl.MultiServiceBImpl)")
    @OSGiService(filter = "(component.name=bmulti)")
    MultiService multiService;

    public String getName(){
        return multiService.getName();
    }
    public Iterator<Page>  getPagesList(){
        return demoServiceA.getPages();
    }

    public List<String> getPageTitlesList(){
        return demoServiceB.getPages();
    }


    public String getNameWithReference() {
        return demoServiceB.getNameWithReference();
    }
}
