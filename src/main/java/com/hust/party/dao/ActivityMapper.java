package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.vo.PerenceActivityVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface ActivityMapper extends BaseMapper<Activity>{


    /**
     * 获取活动列表
     * @param page
     * @return
     */
    List<PerenceActivityVO> getAllActivity(Page page);
    Integer getAllActivityCount();



    /**
     * 获取其他活动
     * @param page
     * @return
     */
    List<PerenceActivityVO>  getQitaActivity(Page page);

    Integer getQitaActivityCount();

    /**
     * 通过传入name获取类别活动
     * @param name
     * @param page
     * @return
     */
    List<PerenceActivityVO> getNameActivity(@Param("name") String name, @Param("page")  Page page);
    Integer getNameActivityCount(String name);
    /**
     * 获取当天已过期的活动列表
     */
    List<Activity> getNowDay(@Param("t") Timestamp t1);

    int updateNowDay(Integer id);


}