package com.practice.mydevelopment.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

@Exporter(name = "jackson",selector = "custom",extensions = "json",
options = {
        @ExporterOption(name="SerializationFeature.WRAP_ROOT_VALUE", value="true"),
        @ExporterOption(name="MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value="true")
})
@JsonRootName("employee-details")
@Model(adaptables = SlingHttpServletRequest.class,
        resourceType = EmployeeInfoModel.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EmployeeInfoModel{

    final protected static String RESOURCE_TYPE = "mydevelopment/components/content/employeeInfo";
    @ValueMapValue
    private String empId;

    @ValueMapValue
    private String empName;

    @ValueMapValue
    private String fileReference;

    @ValueMapValue
    private String empGender;

    @ValueMapValue
    private Date empDOJ;

    @ValueMapValue
    private String empMobileNumber;

    @ValueMapValue
    private String empDesignation;

    @ValueMapValue
    private String empExperience;

    @ValueMapValue
    private String empDataPath;

    @SlingObject
    private Resource empResume;

    @JsonProperty(value = "Employer")
    public String companyName() {
        return "Capgemini";
    }
    @JsonProperty(value = "Employee ID")
    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public String getFileReference() {
        return fileReference;
    }

    public String getEmpGender() {
        return empGender;
    }

    public String getEmpDOJ() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(empDOJ);
    }

    public String getEmpMobileNumber() {
        return empMobileNumber;
    }

    public String getEmpDesignation() {
        return empDesignation;
    }

    public String getEmpExperience() {
        return empExperience;
    }

    public String getEmpDataPath() {
        return empDataPath;
    }

    @JsonIgnore
    public Resource getEmpResume() {
        return empResume;
    }
}