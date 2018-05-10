package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.pojo.*;
import com.hust.party.service.BaseService;
import com.hust.party.util.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description
 * @Author:王焕
 * @Date:2017/7/11
 */

@Transactional
public abstract class AbstractBaseServiceImpl<T> implements BaseService<T> {

    public abstract BaseMapper<T> getDao();

    @Override
    public int deleteByPrimaryKey(Integer code) {
        return getDao().deleteByPrimaryKey(code);
    }

    @Override
    public List<T> select(T record,Page page) {
        if(page==null) {
            page = new Page();
        }
        if(page.getPageNumber()==null||page.getPageSize()==null||page.getPageSize()>=100){
            page.setPageNumber(1);
            page.setPageSize(10);
        }
        return getDao().select(ReflectUtil.generalMap(record,page));
    }

    @Override
    public int selectCount(T record) {
        return getDao().selectCount(record);
    }

    @Override
    public int insert(T record) {
        getDao().insertSelective(record);
        try {
            if(record!=null)
                return  (Integer) ReflectUtil.getValueByName(record,"code");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public T selectByPrimaryKey(Integer code) {
        return getDao().selectByPrimaryKey(code);
    }

    public int update(T record) {
        return getDao().updateByPrimaryKeySelective(record);
    }

    //**************************************************************************************************
    public int update(T record, String userid) {
        int id=update(record);

        return id;
    }

    public int deleteByPrimaryKey(Integer code, String userid) {

        return deleteByPrimaryKey(code);
    }

    public int insert(T record, String userid) {
        int id=insert(record);

        return id;
    }

    public static String getCurrentUserId(HttpServletRequest request){
       if(request.getSession().getAttribute("user")!=null)
            return  ((User)request.getSession().getAttribute("user")).getId();
       return null;
    }



}
