package com.itdreamworks.boilermanage.entity;

import java.io.Serializable;
import java.util.List;

public class ProductUser implements Serializable {

    private Integer id;                 //主键Id
    private Integer productId;          //客户编号
    private Integer userId;             //用户Id
    private List<Role> roleList;    //拥有的角色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
