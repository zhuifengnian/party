package com.hust.party.serviceimpl;

import com.hust.party.dao.ActivityTagMapper;
import com.hust.party.dao.BaseMapper;
import com.hust.party.pojo.ActivityTag;
import com.hust.party.service.ActivityTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luyue on 2018/5/28.
 */
@Service
public class ActivityTagServiceImpl extends  AbstractBaseServiceImpl<ActivityTag> implements ActivityTagService {
   @Autowired
   private ActivityTagMapper activityTagMapper;
    @Override
    public BaseMapper<ActivityTag> getDao() {
        return activityTagMapper;
    }

    @Override
    public List<String> getActivityTag(Integer activityId) {
        return activityTagMapper.getActivityTag(activityId);
    }
}
