package com.hust.party.service;

import com.hust.party.common.Page;
import com.hust.party.pojo.UserStore;
import com.hust.party.vo.UserStoreEnterpriseVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户缴纳金额service<br/>
 * fan 2018/5/24 14:21
 */
public interface UserStoreService extends BaseService<UserStore> {
    /**
     * 根据uid提取收藏信息
     * @param uid
     * @return
     */
    List<UserStoreEnterpriseVO> getUserStore(@Param("uid") Integer uid, @Param("page") Page page);
    Integer getUserStorecount(Integer uid);

    /**
     * 判断是否已经有收藏
     * @param uid
     * @param eid
     * @return
     */
    List <UserStore> judgeStore(@Param("uid") Integer uid,@Param("eid") Integer eid);
}