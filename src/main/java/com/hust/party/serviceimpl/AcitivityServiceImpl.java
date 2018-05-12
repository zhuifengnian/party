package com.hust.party.serviceimpl;

import com.hust.party.dao.ActivityMapper;
import com.hust.party.dao.BaseMapper;
import com.hust.party.pojo.Activity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by luyue on 2018/5/12.
 */
public class AcitivityServiceImpl extends  AbstractBaseServiceImpl<Activity> {
    @Autowired
    ActivityMapper activityMapper;


    @Override
    public BaseMapper<Activity> getDao() {
        return activityMapper;
    }
}
