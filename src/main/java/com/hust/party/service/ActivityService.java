package com.hust.party.service;


import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.vo.PerenceActivityVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityService extends BaseService<Activity>{


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
}
