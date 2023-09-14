package com.practice.mydevelopment.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class StudentInfoModel {

    @Inject
    Resource resource;


    public List<Map<String, String>> getStudentDetails() {
        List<Map<String, String>> studentInfoMap=new ArrayList<>();
        try {
            Resource studentInfo=resource.getChild("studentInfo");
            if(studentInfo!=null){
                for (Resource student : studentInfo.getChildren()) {
                    Map<String,String> studentMap=new HashMap<>();
                    studentMap.put("rollNumber",student.getValueMap().get("rollNumber",String.class));
                    studentMap.put("studentName",student.getValueMap().get("studentName",String.class));
                    studentMap.put("class",student.getValueMap().get("class",String.class));
                    studentMap.put("school",student.getValueMap().get("school",String.class));
                    studentInfoMap.add(studentMap);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return studentInfoMap;
    }

}
