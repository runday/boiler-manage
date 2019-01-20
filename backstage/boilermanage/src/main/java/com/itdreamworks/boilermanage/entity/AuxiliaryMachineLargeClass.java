package com.itdreamworks.boilermanage.entity;

import java.io.Serializable;

/**
 * 辅机大类
 * @date 2018-07-10
 * @author doudou
 */
public class AuxiliaryMachineLargeClass implements Serializable {

    private Integer id;         //id主键
    private String name;       //大类名称
    private int sort;           //排序

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
