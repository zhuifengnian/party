package com.hust.party.service;

import com.hust.party.pojo.Activity;

public interface ActivityService extends BaseService<Activity>{
    Activity getActivity(Integer id);
}
