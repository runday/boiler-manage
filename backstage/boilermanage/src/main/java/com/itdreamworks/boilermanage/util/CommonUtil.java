package com.itdreamworks.boilermanage.util;

import java.lang.reflect.Field;
import java.util.*;

public  class  CommonUtil {
    public static  <T> List<T> duplicateRemovalBean(List<T> list, String IdField){
        List<T> newResource=new ArrayList<>();
        try {
            HashMap<Integer,T> tempMap = new HashMap<Integer,T>();
            for (T t:list){
                Class<? extends Object> beanClass=t.getClass();
                Field field=null;
                if(null!=IdField&&IdField.length()>=0){
                    field=beanClass.getDeclaredField(IdField);
                }else{
                    field=beanClass.getDeclaredField("id");
                }
                field.setAccessible(true); //设置private的属性可以访问
                Object value=field.get(t);
                tempMap.put((Integer)value,t);
            }
            for (T t:tempMap.values()){
                newResource.add(t);
            }
        }catch (Exception e){
            e.getMessage();
        }
        return newResource;
    }
}
