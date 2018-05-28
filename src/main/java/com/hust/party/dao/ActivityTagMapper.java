package com.hust.party.dao;

import com.hust.party.pojo.ActivityTag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityTagMapper extends BaseMapper<ActivityTag>{
    List<String> getActivityTag(Integer activityId);
}