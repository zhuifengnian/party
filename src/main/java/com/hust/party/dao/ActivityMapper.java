package com.hust.party.dao;

import com.hust.party.pojo.Activity;
import org.springframework.stereotype.Repository;
@Repository
public interface ActivityMapper extends BaseMapper<Activity>{
    Activity getActivity(Integer id);
}