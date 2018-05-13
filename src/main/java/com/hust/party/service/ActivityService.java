package com.hust.party.service;


import com.hust.party.pojo.Activity;
import org.springframework.stereotype.Service;

public interface ActivityService extends BaseService<Activity>{
    Activity getActivity(Integer id);
}
