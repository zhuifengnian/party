package com.hust.party.dao;

import com.hust.party.pojo.ActivityPicture;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityPictureMapper  extends BaseMapper<ActivityPicture>{
   List<String > getAllPicture(Integer activityId);
}