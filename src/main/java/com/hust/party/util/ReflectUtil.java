package com.hust.party.util;


import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author:王焕
 * @Date:2017/7/10
 */
public class ReflectUtil {

    public static Object getValueByName(Object obj,String name) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return PropertyUtils.getProperty(obj,name);

    }

    public static Object getValueByNames(Object obj,String ...name) {
        for(String n:name){
            Object v= null;
            try {
                v = PropertyUtils.getProperty(obj,n);
            } catch (Exception e) {}
            if(v!=null)
                return v;
        }
        return null;
    }

    public static Map<String,Object> generalMap(Object ...obj){
        Map<String,Object> map=new HashMap<>();
        Map temMap;
        for(Object o:obj) {
            try {
                temMap=PropertyUtils.describe(o);
                if(temMap!=null&&temMap.size()>0){
                    map.putAll(temMap);
                }
            } catch (Exception e) {

            }
        }
        map.remove("class");
        return map;
    }

}
