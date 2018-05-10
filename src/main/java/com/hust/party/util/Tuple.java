package com.hust.party.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author:王焕
 * @Date:2017/7/18
 */
public class Tuple {
    public List tuple=new ArrayList();

    public int getSize(){
        return tuple.size();
    }

    public Object[] getTuple(){
        return tuple.toArray();
    }

    public void set(Object ...obj){
        tuple.clear();
        Collections.addAll(tuple,obj);
    }

    public Tuple(Object ...obj){
        tuple.clear();
        Collections.addAll(tuple,obj);
    }

}
