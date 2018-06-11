package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.User;
import com.hust.party.pojo.UserStore;
import com.hust.party.vo.EnterpriseInfoVO;
import com.hust.party.vo.UserInfoVO;
import com.hust.party.vo.UserStoreEnterpriseVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoreMapper extends BaseMapper<UserStore>{
    /**
     * 根据uid提取收藏信息
     * @param uid
     * @return
     */
    List<UserStoreEnterpriseVO> getUserStore(@Param("uid") Integer uid,@Param("page") Page page);
    Integer getUserStorecount(Integer uid);

    /**
     * 判断是否已经有收藏
     * @param uid
     * @param eid
     * @return
     */
   List <UserStore> judgeStore(@Param("uid") Integer uid,@Param("eid") Integer eid);

}