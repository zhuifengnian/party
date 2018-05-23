package com.hust.party.common;


import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


public class ReflectUtil {



    public static Map<String,Object> generalMap(Object ...obj){
        Map<String,Object> map=new HashMap<>();
        Map temMap;
        for(Object o:obj) {
            try {
                temMap= PropertyUtils.describe(o);
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
