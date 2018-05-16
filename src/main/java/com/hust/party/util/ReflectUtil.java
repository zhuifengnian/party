package com.hust.party.util;


import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装反射有关的方法的类，比如参数复制，通过反射获取值与赋值，将对象的字段与值放入map中
 */
public class ReflectUtil {

    /**
     * 封装复制参数的方法
     * @param dest  目标赋予对象
     * @param src   源对象
     */
    public static void copyProperties(Object dest, Object src){
        try {
            PropertyUtils.copyProperties(dest, src);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

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
