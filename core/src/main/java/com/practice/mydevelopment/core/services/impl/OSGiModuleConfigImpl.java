package com.practice.mydevelopment.core.services.impl;

import com.practice.mydevelopment.core.config.CustomServiceConfig;
import com.practice.mydevelopment.core.services.OSGiConfig;
import com.practice.mydevelopment.core.services.OSGiModuleConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = OSGiModuleConfig.class,immediate = true)
@Designate(ocd= CustomServiceConfig.class)
public class OSGiModuleConfigImpl implements OSGiModuleConfig {

    private String name;
    private int id;
    private boolean isEmployee;
    private String[] companies;
    private String domains;
    @Activate
    protected void activate(CustomServiceConfig customServiceConfig){
        name = customServiceConfig.getName();
        id = customServiceConfig.getId();
        isEmployee = customServiceConfig.isEmployee();
        companies = customServiceConfig.getCompanies();
        domains = customServiceConfig.getDomains();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isEmployee() {
        return isEmployee;
    }

    @Override
    public String[] getCompanies() {
        return companies;
    }

    @Override
    public String getDomains() {
        return domains;
    }
}
