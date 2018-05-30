package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.ActivityMapper;
import com.hust.party.dao.BaseMapper;
import com.hust.party.pojo.Activity;
import com.hust.party.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luyue on 2018/5/12.
 */
@Service("activityService")
public class AcitivityServiceImpl extends  AbstractBaseServiceImpl<Activity> implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public BaseMapper<Activity> getDao() {
        return activityMapper;
    }

    @Override
    public List<Activity> getEnterpriseActivity(Integer eid, Page page) {
        return activityMapper.getEnterpriseActivity(eid,page);
    }

    @Override
    public List<Activity> getAllActivity(Page page) {
        return activityMapper.getAllActivity(page);
    }

    @Override
    public List<Activity> getAllCurrentActivity(Integer eid) {
        return activityMapper.getAllCurrentActivity(eid);
    }


}
