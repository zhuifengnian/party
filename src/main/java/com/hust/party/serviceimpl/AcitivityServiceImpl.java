package com.hust.party.serviceimpl;

import com.hust.party.dao.ActivityMapper;
import com.hust.party.dao.ActivityMapper2;
import com.hust.party.dao.BaseMapper;
import com.hust.party.pojo.Activity;
import com.hust.party.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luyue on 2018/5/12.
 */
@Service("activityService")
public class AcitivityServiceImpl extends  AbstractBaseServiceImpl<Activity> implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    ActivityMapper2 activityMapper2;




    @Override
    public BaseMapper<Activity> getDao() {
        return activityMapper;
    }

    @Override
    public Activity getActivity(Integer id) {
        return activityMapper2.getActivity(id);
    }
}
