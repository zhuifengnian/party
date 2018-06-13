package com.hust.party.dao;

import com.hust.party.pojo.KuaiDiExpress;
import com.hust.party.vo.KuaidiExpressVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KuaiDiExpressMapper extends  BaseMapper<KuaiDiExpress>  {
    /**
     * 通过驿站名获取信息
     * @param express_station
     * @return
     */
        List<KuaiDiExpress> getListKuaiinfo(@Param("express_station") String express_station);
}