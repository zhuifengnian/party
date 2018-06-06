package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.vo.PerenceActivityVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityMapper extends BaseMapper<Activity>{
    List<Activity> getEnterpriseActivity(Integer eid,Page page);
    List<PerenceActivityVO> getAllActivity(Page page);
    List<Activity> getAllCurrentActivity( Integer eid);
    List<PerenceActivityVO>  getQitaActivity(Page page);
    List<PerenceActivityVO> getNameActivity(@Param("name") String name, @Param("page")  Page page);
}