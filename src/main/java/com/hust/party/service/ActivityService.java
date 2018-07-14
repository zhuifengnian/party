package com.hust.party.service;


import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.pojo.Activity;
import com.hust.party.vo.PerenceActivityVO;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.Date;
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
    /**
     * 获取当天已过期的活动列表
     */
    List<Activity> getNowDay(@Param("t") Timestamp t1);
    int updateNowDay(Integer id);
    /**
     * 查看商户所有活动
     *
     * @param id
     * @return
     */
    List<PerenceActivityVO> getEnterpriseAllActivity(@Param("id") Integer id, @Param("page") Page page);

    Integer getEnterpriseAllActivityCount(Integer id);

    /**
     * 获得商户正上线活动
     *
     * @param id
     * @return
     */
    List<PerenceActivityVO> getEnterpriseNowActivity(@Param("id") Integer id, @Param("page") Page page);

    Integer getEnterpriseNowActivityCount(Integer id);


    /**
     * 获取商户已删除活动
     *
     * @param id
     * @return
     */
    List<PerenceActivityVO> getEnterpriseDeleteActivity(@Param("id") Integer id, @Param("page") Page page);

    Integer getEnterpriseDeleteActivityCount(Integer id);
    /**
     * 获取企业活动
     *
     * @param name
     * @param id
     * @param page
     * @return
     */
    PageInfo<PerenceActivityVO> getEnterpriseActivity(String name, Integer id, Page page);
}
