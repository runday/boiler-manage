package com.itdreamworks.boilermanage.entity;

import java.io.Serializable;

/**
 * 字典
 * @date 2018-07-10
 * @author doudou
 */
public class Dictionary implements Serializable {
    private Integer id;         //id主键
    private String name;    //字典名称
    private String type;    //字典类型
    private int sort;       //字典排序

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
