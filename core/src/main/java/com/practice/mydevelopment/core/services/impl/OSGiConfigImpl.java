package com.practice.mydevelopment.core.services.impl;

import com.practice.mydevelopment.core.services.OSGiConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.*;

@Component(service = OSGiConfig.class,immediate = true)
@Designate(ocd=OSGiConfigImpl.OSGiServiceConfig.class)
public class OSGiConfigImpl implements OSGiConfig {

    @ObjectClassDefinition(name = "My Dev - OSGi Configuration",
            description = "OSGi Configuration Demo")
    public @interface OSGiServiceConfig {

        @AttributeDefinition(
                name = "Service Name",
                description = "Enter the Service name",
                type = AttributeType.STRING
        )
        public String getServiceName() default "Custom Dev Service";

        @AttributeDefinition(
                name = "Service Count",
                description = "Add Service Count",
                type = AttributeType.INTEGER
        )
        public int getServiceCount() default 5;

        @AttributeDefinition(
                name = "Live Data",
                description = "Check this to get live data",
                type = AttributeType.BOOLEAN
        )
        public boolean isLiveData() default false;

        @AttributeDefinition(
                name = "Countries",
                description = "Add countries to which you want to add the service",
                type = AttributeType.STRING
        )
        public String[] getCountries() default {"in", "fr"};

        @AttributeDefinition(
                name = "Run Modes",
                description = "Select any run mode",
                options = {
                        @Option(label = "Author", value = "author"),
                        @Option(label = "Publish", value = "publish"),
                        @Option(label = "Both", value = "both")

                },
                type = AttributeType.STRING
        )
        public String getRunmode() default "both";

    }

    private String serviceName;
    private int serviceCount;
    private boolean isLiveData;
    private String[] countries;
    private String runModes;
    @Activate
    protected void activate(OSGiServiceConfig oSGiServiceConfig){
        serviceName = oSGiServiceConfig.getServiceName();
        serviceCount = oSGiServiceConfig.getServiceCount();
        isLiveData = oSGiServiceConfig.isLiveData();
        countries = oSGiServiceConfig.getCountries();
        runModes = oSGiServiceConfig.getRunmode();
    }
    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public int getServiceCount() {
        return serviceCount;
    }

    @Override
    public boolean isLiveData() {
        return isLiveData;
    }

    @Override
    public String[] getCountries() {
        return countries;
    }

    @Override
    public String getRunModes() {
        return runModes;
    }
}
