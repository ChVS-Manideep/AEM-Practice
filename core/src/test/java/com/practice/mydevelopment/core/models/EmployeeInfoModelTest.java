package com.practice.mydevelopment.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class EmployeeInfoModelTest {

    private AemContext ctx = new AemContext();
    @BeforeEach
    void setUp() {
        ctx.addModelsForClasses(EmployeeInfoModel.class);
        ctx.load().json("/com.practice.mydevelopment.core.jsons/employee-info.json", "/content/mydevelopment/us/en");
    }

    @Test
    void getEmpId() {
        ctx = getContext();
        EmployeeInfoModel model = getModelObj(ctx);
        assertEquals("46082493", model.getEmpId());
    }

    @Test
    void getEmpName() {
        ctx = getContext();
        EmployeeInfoModel model = getModelObj(ctx);
        assertEquals("Manideep", model.getEmpName());
    }

    @Test
    void getFileReference() {
        ctx = getContext();
        EmployeeInfoModel model = getModelObj(ctx);
        assertEquals("/content/dam/magneto/assets/png-transparent-check-correct-tick-sign-mark-orange-thumbnail-removebg-preview.png", model.getFileReference());
    }

    @Test
    void getEmpGender() {
        ctx = getContext();
        EmployeeInfoModel model = getModelObj(ctx);
        assertEquals("Male", model.getEmpGender());
    }

    @Test
    void getEmpDOJ() {
        ctx = getContext();
        EmployeeInfoModel model = getModelObj(ctx);
        assertEquals("16-11-2023", model.getEmpDOJ());
    }

    @Test
    void getEmpMobileNumber() {
        ctx = getContext();
        EmployeeInfoModel model = getModelObj(ctx);
        assertEquals("9391383121", model.getEmpMobileNumber());
    }

    @Test
    void getEmpDesignation() {
        ctx = getContext();
        EmployeeInfoModel model = getModelObj(ctx);
        assertEquals("Manideep", model.getEmpName());
    }

    @Test
    void getEmpExperience() {
        ctx = getContext();
        EmployeeInfoModel model = getModelObj(ctx);
        assertEquals("true", model.getEmpExperience());
    }

    @Test
    void getEmpDataPath() {
        ctx = getContext();
        EmployeeInfoModel model = getModelObj(ctx);
        assertEquals("/content/mydevelopment/us/en", model.getEmpDataPath());
    }


    private AemContext getContext(){
        ctx.currentResource("/content/mydevelopment/us/en/jcr:content/root/responsivegrid/employeeinfo");
        return ctx;
    }
    private EmployeeInfoModel getModelObj(AemContext ctx){
        EmployeeInfoModel model = ctx.request().adaptTo(EmployeeInfoModel.class);
        return model;
    }
}