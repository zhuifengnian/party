package com.hust.party.service;


import com.hust.party.common.Page;
import com.hust.party.pojo.Comment;
import com.hust.party.pojo.KuaiDiExpress;
import com.hust.party.vo.CommentVo;
import com.hust.party.vo.KuaidiExpressVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KuaiDiExpressService extends BaseService<KuaiDiExpress>{
    /**
     * 通过大使驿站名获取信息
     * @param
     * @return
     */
    List<KuaiDiExpress> getListKuaiinfo(@Param("school_id") Integer school_id,@Param("page")  Page page);
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
