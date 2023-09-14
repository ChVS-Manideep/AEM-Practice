package com.practice.mydevelopment.core.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VehicleInfoBean {

    private String vehicleType;
    private String manufacturer;

    private Date year;

    private String price;

    private List<VehicleSpecsBean> vehicleSpecs;

    public VehicleInfoBean(String vehicleType, String manufacturer, Date year, String price, List<VehicleSpecsBean> vehicleSpecs) {
        this.vehicleType = vehicleType;
        this.manufacturer = manufacturer;
        this.year = year;
        this.price = price;
        this.vehicleSpecs = vehicleSpecs;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getYear() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(year);
    }

    public String getPrice() {
        return price;
    }

    public List<VehicleSpecsBean> getVehicleSpecs() {
        return vehicleSpecs;
    }
}
