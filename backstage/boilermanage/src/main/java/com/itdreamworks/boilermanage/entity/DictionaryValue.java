package com.itdreamworks.boilermanage.entity;

import java.io.Serializable;
/**
 * 字典值
 * @date 2018-07-10
 * @author doudou
 */
public class DictionaryValue implements Serializable {

    private Integer id;         //id主键
    private String type;        //字典类型
    private String label;       //字典名称
    private int value;          //字典值
    private int sort;           //字典排序

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
