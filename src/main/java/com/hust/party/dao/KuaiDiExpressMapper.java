package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.KuaiDiExpress;
import com.hust.party.vo.KuaidiExpressVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KuaiDiExpressMapper extends  BaseMapper<KuaiDiExpress>  {
    /**
     * 通过大使驿站名获取信息
     * @param
     * @return
     */
        List<KuaiDiExpress> getListKuaiinfo(@Param("school_id") Integer school_id,@Param("page") Page page);
    /**
     * 通过大使驿站名获取信息数量
     * @param
     * @return
     */
   Integer getListKuaiinfoCount(@Param("school_id") Integer school_id);
    /**
     * 直接选取所有地址
     */
    List<KuaiDiExpress>getListKuaiinfo1();
}