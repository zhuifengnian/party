package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityMapper extends BaseMapper<Activity>{
    List<Activity> getEnterpriseActivity(Integer eid,Page page);
    List<Activity>getAllActivity(Page page);
    List<Activity> getAllCurrentActivity( Integer eid);
}