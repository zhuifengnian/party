package com.hust.party.service;


import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.ActivityTag;

import java.util.List;

public interface ActivityTagService extends BaseService<ActivityTag>{
    List<String> getActivityTag(Integer activityId);

}
