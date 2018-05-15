package com.hust.party.service;

import com.hust.party.pojo.Activity;

import java.util.List;

public interface ActivityService extends BaseService<Activity>{
    Activity getActivity(Integer id);
    List<Activity> getEnterpriseActivity(Integer eid);
}
