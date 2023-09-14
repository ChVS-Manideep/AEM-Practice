package com.practice.mydevelopment.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.practice.mydevelopment.core.beans.TeamMembersInfoBean;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Exporter(name="jackson",extensions = "json",selector = "dev",
        options = {
                @ExporterOption(name="SerializationFeature.WRAP_ROOT_VALUE", value="true"),
                @ExporterOption(name="MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value="true")
        })
@Model(adaptables = SlingHttpServletRequest.class,
        resourceType = TeamMembersInfoModel.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@JsonRootName("team-members-informationn")
public class TeamMembersInfoModel {

    final protected static String RESOURCE_TYPE = "mydevelopment/components/content/teamMembersInfo";
    @SlingObject
    Resource componentResource;

    @SlingObject
    protected Resource resource;


    public List<TeamMembersInfoBean> getTeamMembersInfo() {
        List<TeamMembersInfoBean> teamInfo = new ArrayList<>();

//        try {
//            Resource fields=componentResource.getChild("teamMemberInfo");
//            if(fields!=null){
//                for (Resource child : fields.getChildren()) {
//                    teamInfo.add(getInfo(child));
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        Optional<Resource> fields = Optional.ofNullable(componentResource.getChild("teamMemberInfo"));
        fields.ifPresent(resource -> {
            for (Resource child : resource.getChildren()) {
                teamInfo.add(getInfo(child));
            }
        });
        return teamInfo;
    }


    private TeamMembersInfoBean getInfo(Resource resource){
        ValueMap vm = resource.getValueMap();
         String name;
         String role;
         String email;
         String bio;

         name =vm.get("name",String.class);
         role =vm.get("role",String.class);
         email =vm.get("email",String.class);
         bio =vm.get("bio",String.class);

         return new TeamMembersInfoBean(name,role,email,bio);
    }

    @JsonProperty(value="team-name")
    public String teamName(){
        return "Foxtel/Magneto";
    }
}
