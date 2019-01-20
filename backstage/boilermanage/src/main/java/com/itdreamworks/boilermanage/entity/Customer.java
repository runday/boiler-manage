package com.itdreamworks.boilermanage.entity;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {
    private Integer id, enterpriseId,status;
    private String customerName,customerNo;
    private List<CustomerResource>  customerResourceList;
    private List<CustomerUser> customerUserList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public List<CustomerResource> getCustomerResourceList() {
        return customerResourceList;
    }

    public void setCustomerResourceList(List<CustomerResource> customerResourceList) {
        this.customerResourceList = customerResourceList;
    }

    public List<CustomerUser> getCustomerUserList() {
        return customerUserList;
    }

    public void setCustomerUserList(List<CustomerUser> customerUserList) {
        this.customerUserList = customerUserList;
    }
}
