package com.practice.mydevelopment.core.models;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;


@Exporter(name="xmlcustomexporter",extensions = "xml",selector = "dev")
@Model(adaptables = Resource.class,adapters = SignUpFormModel.class,
        resourceType = SignUpFormModel.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@XmlRootElement(name="signin-data")
public class SignUpFormModel {

    final protected static String RESOURCE_TYPE = "mydevelopment/components/content/signup";
    @Inject @Named("firstName")
    private String firstName;

    @Inject @Named("lastName")
    private String lastName;

    @Inject @Named("phoneNumber")
    private String phoneNumber;

    @Inject @Named("email")
    private String email;

    @Inject @Named("gender")
    private String gender;

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @XmlElement
    public String getEmail() {
        return email;
    }

    @XmlElement
    public String getGender() {
        return gender;
    }
}
