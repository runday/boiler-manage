package com.itdreamworks.boilermanage.entity;

import java.io.Serializable;

public class ProductAuxiliaryMachineInfo implements Serializable {

    private Integer id,productId,largeClassId,smallClassId,amountOfUser;
    private String brandName,modelName,supplier,remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getLargeClassId() {
        return largeClassId;
    }

    public void setLargeClassId(Integer largeClassId) {
        this.largeClassId = largeClassId;
    }

    public Integer getSmallClassId() {
        return smallClassId;
    }

    public void setSmallClassId(Integer smallClassId) {
        this.smallClassId = smallClassId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getAmountOfUser() {
        return amountOfUser;
    }

    public void setAmountOfUser(Integer amountOfUser) {
        this.amountOfUser = amountOfUser;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
