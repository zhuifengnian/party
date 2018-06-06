package com.hust.party.service;


import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.vo.PerenceActivityVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityService extends BaseService<Activity>{
    List<Activity> getEnterpriseActivity(Integer eid,Page page);
    List<PerenceActivityVO> getAllActivity(Page page);
    List<Activity> getAllCurrentActivity(Integer eid);
    List<PerenceActivityVO>  getQitaActivity(Page page);
    List<PerenceActivityVO> getNameActivity(@Param("name") String name, @Param("page")  Page page);

}
