package com.practice.mydevelopment.core.beans;

public class SelectiveDropdownProductsBean {
    private String productName;
    private String price;
    private String productDescription;

    public SelectiveDropdownProductsBean(String productName, String price, String productDescription) {
        this.productName = productName;
        this.price = price;
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public String getProductDescription() {
        return productDescription;
    }
}
