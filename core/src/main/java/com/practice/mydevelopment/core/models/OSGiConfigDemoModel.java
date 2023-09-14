package com.practice.mydevelopment.core.models;

import com.practice.mydevelopment.core.services.OSGiConfig;
import com.practice.mydevelopment.core.services.OSGiFactoryConfig;
import com.practice.mydevelopment.core.services.OSGiModuleConfig;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGiConfigDemoModel {

    @OSGiService
    OSGiConfig oSGiConfig;

    @OSGiService
    OSGiModuleConfig oSGiModuleConfig;

    public String getName(){
        return  oSGiModuleConfig.getName();
    }

    public int getId(){
        return  oSGiModuleConfig.getId();
    }

    public boolean isEmployee(){
        return  oSGiModuleConfig.isEmployee();
    }
    public String[] getCompanies(){
        return  oSGiModuleConfig.getCompanies();
    }

    public String getDomains(){
        return  oSGiModuleConfig.getDomains();
    }
    public String getServiceName() {
        return oSGiConfig.getServiceName();
    }
    public int getServiceCount() {
        return oSGiConfig.getServiceCount();
    }
    public boolean isLiveData() {
        return oSGiConfig.isLiveData();
    }
    public String[] getCountries() {
        return oSGiConfig.getCountries();
    }
    public String getRunModes() {
        return oSGiConfig.getRunModes();
    }
//Factory Config
    @OSGiService
    OSGiFactoryConfig oSGiFactoryConfig;
//    public List<OSGiFactoryConfig> getAllOSGiConfigs() {
//        return oSGiFactoryConfig.getAllConfigs();
//    }
}
