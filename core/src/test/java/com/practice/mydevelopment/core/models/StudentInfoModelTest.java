package com.practice.mydevelopment.core.models;

import com.practice.mydevelopment.core.beans.SelectiveDropdownArticlesBean;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class StudentInfoModelTest {

    private AemContext ctx = new AemContext();
    @BeforeEach
    void setUp() {
        ctx.addModelsForClasses(StudentInfoModel.class);
        ctx.load().json("/com.practice.mydevelopment.core.jsons/student-info.json", "/content/mydevelopment/us/en");
    }

//    @Test
//    void getStudentDetails() {
//        ctx = getContext();
//        StudentInfoModel model = getModelObj(ctx);
//
//        if (model.getStudentDetails() != null) {
//            ArrayList<Map<String, String>> list =new ArrayList<>();
//            list = (ArrayList<Map<String, String>>) model.getStudentDetails();
//            assertEquals("", list.get(0).g());
//
//        }
//    }

    private AemContext getContext(){
        ctx.currentResource("/content/mydevelopment/us/en/jcr:content/root/responsivegrid/studentinfo");
        return ctx;
    }
    private StudentInfoModel getModelObj(AemContext ctx){
        StudentInfoModel model = ctx.request().adaptTo(StudentInfoModel.class);
        return model;
    }
}