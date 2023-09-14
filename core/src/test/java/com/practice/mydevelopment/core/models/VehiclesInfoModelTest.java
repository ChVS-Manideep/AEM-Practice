package com.practice.mydevelopment.core.models;

import com.practice.mydevelopment.core.beans.SelectiveDropdownEventsBean;
import com.practice.mydevelopment.core.beans.VehicleInfoBean;
import com.practice.mydevelopment.core.beans.VehicleSpecsBean;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class VehiclesInfoModelTest {

    private AemContext ctx = new AemContext();
    @BeforeEach
    void setUp() {
        ctx.addModelsForClasses(TeamMembersInfoModel.class);
        ctx.load().json("/com.practice.mydevelopment.core.jsons/vehicle-info.json", "/content/mydevelopment/us/en");
    }
    @Test
    void getVehiclesInfo() {

        ctx = getContext();
        VehiclesInfoModel model = getModelObj(ctx);

        if (model.getVehiclesInfo() != null) {
            ArrayList<VehicleInfoBean> list = new ArrayList<>();
            list = (ArrayList<VehicleInfoBean>) model.getVehiclesInfo();
            assertEquals("Tesla", list.get(1).getManufacturer());
            assertEquals("4-Wheeler", list.get(1).getVehicleType());
            assertEquals("11100000", list.get(1).getPrice());
            assertEquals("2023", list.get(1).getYear());

            ArrayList<VehicleSpecsBean> inner = new ArrayList<>();
            inner = (ArrayList<VehicleSpecsBean>) list.get(0).getVehicleSpecs();
            assertEquals("Engine Type", inner.get(0).getSpecification());
            assertEquals("Petrol Engine", inner.get(0).getSpecificationValue());

        }
    }

    @Test
    void getState() {
        ctx = getContext();
        VehiclesInfoModel model = getModelObj(ctx);
        assertEquals("AP", model.getState());
    }

    @Test
    void getPincode() {
        ctx = getContext();
        VehiclesInfoModel model = getModelObj(ctx);
        assertEquals("534301", model.getPincode());
    }
    private AemContext getContext(){
        ctx.currentResource("/content/mydevelopment/us/en/jcr:content/root/responsivegrid/vehiclesinfo");
        return ctx;
    }
    private VehiclesInfoModel getModelObj(AemContext ctx){
        VehiclesInfoModel model = ctx.request().adaptTo(VehiclesInfoModel.class);
        return model;
    }
}