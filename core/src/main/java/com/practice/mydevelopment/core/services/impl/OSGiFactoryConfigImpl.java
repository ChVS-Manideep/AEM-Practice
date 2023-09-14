package com.practice.mydevelopment.core.services.impl;

import com.practice.mydevelopment.core.config.CustomOSGiFactoryConfig;
import com.practice.mydevelopment.core.services.OSGiFactoryConfig;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@Component(service = OSGiFactoryConfigImpl.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = CustomOSGiFactoryConfig.class, factory = true)
public class OSGiFactoryConfigImpl implements OSGiFactoryConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(OSGiFactoryConfigImpl.class);

    private int configID;
    private String serviceName;
    private String serviceURL;
    private List<OSGiFactoryConfig> configsList;

    @Activate
    @Modified
    protected void activate(final CustomOSGiFactoryConfig config) {
        configID = config.configID();
        serviceName = config.serviceName();
        serviceURL = config.serviceURL();
    }

    @Reference(service = OSGiFactoryConfig.class, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindOSGiFactoryConfig(final OSGiFactoryConfig config) {
        if (configsList == null) {
            configsList = new ArrayList<>();
        }
        configsList.add(config);

    }

    public void unbindOSGiFactoryConfig(final OSGiFactoryConfig config) {
        configsList.remove(config);
    }

    @Override
    public int getConfigID() {
        return configID;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getServiceURL() {
        return serviceURL;
    }


    @Override
    public List<OSGiFactoryConfig> getAllConfigs() {
        return configsList;
    }

    @Override
    public OSGiFactoryConfig get(int configID) {
        for (OSGiFactoryConfig confFact : configsList) {
            if (configID == confFact.getConfigID())
                return confFact;
        }
        return null;
    }
}