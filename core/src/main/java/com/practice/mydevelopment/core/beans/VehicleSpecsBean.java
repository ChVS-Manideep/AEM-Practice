package com.practice.mydevelopment.core.beans;

public class VehicleSpecsBean {
    private String specification;
    private String specificationValue;

    public VehicleSpecsBean(String specification, String specificationValue) {
        this.specification = specification;
        this.specificationValue = specificationValue;
    }

    public String getSpecification() {
        return specification;
    }

    public String getSpecificationValue() {
        return specificationValue;
    }
}
