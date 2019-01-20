package com.itdreamworks.boilermanage.entity;

import java.io.Serializable;

/**
 * 锅炉型号
 * @date 2018-08-17
 * @author doudou
 */
public class BoilerModel implements Serializable {

    private Integer id;         //id主键
    private String label;       //字典名称
    private Integer value;          //字典值
    private Integer orgType;        //组织类型
    private String orgId;       //组织Id
    private Integer sort;           //字典排序

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
