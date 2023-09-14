package com.practice.mydevelopment.core.models;

import com.practice.mydevelopment.core.beans.VehicleInfoBean;
import com.practice.mydevelopment.core.beans.VehicleSpecsBean;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VehiclesInfoModel {

    @SlingObject
    Resource componentResource;
    @ValueMapValue
    private String state;

    @ValueMapValue
    private String pincode;

    public List<VehicleInfoBean> getVehiclesInfo() {
        List<VehicleInfoBean> vehicleInfo = new ArrayList<>();
        try {
            Resource fields = componentResource.getChild("vehicleInfo");
            if (fields != null) {
                for (Resource child : fields.getChildren()) {
                    vehicleInfo.add(getInfo(child));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicleInfo;
    }

    private VehicleInfoBean getInfo(Resource resource) {
        ValueMap vm = resource.getValueMap();

        String vehicleType = vm.get("vehicleType", String.class);
        String manufacturer = vm.get("manufacturer", String.class);
        Date year = vm.get("year", Date.class);
        String price = vm.get("price", String.class);

        Resource specsResource = resource.getChild("vehicleSpecs");

        List<VehicleSpecsBean> vehicleSpecs = new ArrayList<>();

        if (specsResource != null) {
            for (Resource specResource : specsResource.getChildren()) {
                VehicleSpecsBean specBean = getSpecsInfo(specResource);
                vehicleSpecs.add(specBean);
            }
        }
        return new VehicleInfoBean(vehicleType, manufacturer, year, price, vehicleSpecs);
    }

    private VehicleSpecsBean getSpecsInfo(Resource resource) {
        ValueMap vm = resource.getValueMap();
        String specification;
        String specificationValue;

        specification = vm.get("specification", String.class);
        specificationValue = vm.get("specificationValue", String.class);

        return new VehicleSpecsBean(specification, specificationValue);
    }

    public String getState() {
        return state;
    }

    public String getPincode() {
        return pincode;
    }
}