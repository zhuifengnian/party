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
     * 通过驿站名获取信息
     * @param express_station
     * @return
     */
    List<KuaiDiExpress> getListKuaiinfo(@Param("express_station") String express_station);
    /**
     * 直接选取所有地址
     */
    List<KuaiDiExpress>getListKuaiinfo1();

}
