package com.practice.mydevelopment.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "My Dev - OSGi Modular Config",
description = "OCD written in a seperate file")
public @interface CustomServiceConfig {

    @AttributeDefinition(
            name = "Name",
            description = "Enter the name",
            type = AttributeType.STRING
    )
     String getName() default "Mani";

    @AttributeDefinition(
            name = "ID",
            description = "Add Id",
            type = AttributeType.INTEGER
    )
     int getId() default 5;

    @AttributeDefinition(
            name = "Is Employee",
            description = "Check this if user is employee",
            type = AttributeType.BOOLEAN
    )
     boolean isEmployee() default false;

    @AttributeDefinition(
            name="Companies",
            description = "Add companies to which you want to add the servic",
            type=AttributeType.STRING
    )
    String[] getCompanies() default {"Capg","Infy"};
    @AttributeDefinition(
            name = "Domains",
            description = "Select any Domain",
            options = {
                    @Option(label = "Front-end", value = "frontend"),
                    @Option(label = "Back-end", value = "backend"),
                    @Option(label = "Both", value = "both")

            },
            type = AttributeType.STRING
    )
    public String getDomains() default "both";


}
