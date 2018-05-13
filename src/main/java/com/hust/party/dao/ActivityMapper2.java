package com.hust.party.dao;

import com.hust.party.pojo.Activity;
import org.springframework.stereotype.Repository;

public interface ActivityMapper2 {
    Activity getActivity(Integer id);
}