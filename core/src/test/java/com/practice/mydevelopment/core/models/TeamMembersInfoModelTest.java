package com.practice.mydevelopment.core.models;

import com.practice.mydevelopment.core.beans.SelectiveDropdownEventsBean;
import com.practice.mydevelopment.core.beans.TeamMembersInfoBean;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class TeamMembersInfoModelTest {

    private AemContext ctx = new AemContext();
    @BeforeEach
    void setUp() {
        ctx.addModelsForClasses(TeamMembersInfoModel.class);
        ctx.load().json("/com.practice.mydevelopment.core.jsons/team-members-info.json", "/content/mydevelopment/us/en");
    }
    @Test
    void getTeamMembersInfo() {
        ctx = getContext();
        TeamMembersInfoModel model = getModelObj(ctx);

        if (model.getTeamMembersInfo() != null) {
            ArrayList<TeamMembersInfoBean> list = new ArrayList<>();
            list = (ArrayList<TeamMembersInfoBean>) model.getTeamMembersInfo();
            assertEquals("Deep", list.get(1).getName());
            assertEquals("Lead", list.get(1).getRole());
            assertEquals("deep@gmail.com", list.get(1).getEmail());
            assertEquals("AEM Lead", list.get(1).getBio());
        }
    }

    private AemContext getContext(){
        ctx.currentResource("/content/mydevelopment/us/en/jcr:content/root/responsivegrid/teammembersinfo");
        return ctx;
    }
    private TeamMembersInfoModel getModelObj(AemContext ctx){
        TeamMembersInfoModel model = ctx.request().adaptTo(TeamMembersInfoModel.class);
        return model;
    }
}