package com.hust.party.service;


import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.ActivityPicture;

import java.util.List;

public interface ActivityPictureService extends BaseService<ActivityPicture>{
    List<String > getAllPicture(Integer activityId);

}
