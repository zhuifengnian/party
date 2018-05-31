package com.hust.party.service;


import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;

import java.util.List;

public interface ActivityService extends BaseService<Activity>{
    List<Activity> getEnterpriseActivity(Integer eid,Page page);
    List<Activity>getAllActivity(Page page);
    List<Activity> getAllCurrentActivity(Integer eid);
    List<Activity> getQitaActivity(Page page);
}
