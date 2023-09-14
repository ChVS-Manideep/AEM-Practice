package com.practice.mydevelopment.core.models;

import com.day.cq.wcm.api.Page;
import com.practice.mydevelopment.core.config.CustomContextAwareConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.caconfig.ConfigurationResolver;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Model(adaptables = {SlingHttpServletRequest.class},

        resourceType = {CAConfigModel.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CAConfigModel  {
    private static final Logger LOG = LoggerFactory.getLogger(CAConfigModel.class);
    protected static final String RESOURCE_TYPE = "mydevelopment/components/content/caConfigDemo";

    @SlingObject
    ResourceResolver resourceResolver;

    @ScriptVariable
    Page currentPage;

    @OSGiService
    ConfigurationResolver configurationResolver;

    private String siteCountry;
    private String siteLocale;
    private String siteAdmin;
    private String siteSection;
    private CustomContextAwareConfig geeksCAConfig;

    public String getSiteCountry() {
        return siteCountry;
    }

    public String getSiteLocale() {
        return siteLocale;
    }

    public String getSiteAdmin() {
        return siteAdmin;
    }

    public String getSiteSection() {
        return siteSection;
    }

    @PostConstruct
    public void postConstruct() {
        CustomContextAwareConfig caConfig=getContextAwareConfig(currentPage.getPath(),resourceResolver);
        siteCountry=caConfig.siteCountry();
        siteLocale=caConfig.siteLocale();
        siteAdmin=caConfig.siteAdmin();
        siteSection=caConfig.siteSection();
    }

    public CustomContextAwareConfig getContextAwareConfig(String currentPage, ResourceResolver resourceResolver) {
        String currentPath = StringUtils.isNotBlank(currentPage) ? currentPage : StringUtils.EMPTY;
        Resource contentResource = resourceResolver.getResource(currentPath);
        if (contentResource != null) {
            ConfigurationBuilder configurationBuilder = contentResource.adaptTo(ConfigurationBuilder.class);
            if (configurationBuilder != null) {
                return configurationBuilder.as(CustomContextAwareConfig.class);
            }
        }
        return null;
    }
}
