package com.practice.mydevelopment.core.config;

import org.apache.sling.caconfig.annotation.Configuration;
import org.apache.sling.caconfig.annotation.Property;

@Configuration(label = "Custom Context Aware Configuration",description = "CA Config from My Development Project")
public @interface CustomContextAwareConfig {
    @Property(label = "Country Site",description = "Site Name")
    String siteCountry() default "us";

    @Property(label = "Site Locale",description = "Site for different Languages")
    String siteLocale() default "en";

    @Property(label = "Site Admin",description = "Admin for updating Country site")
    String siteAdmin() default "AEM-dev";

    @Property(label = "Site Section", description = "Site section for sites")
    String siteSection() default "aem";
}
