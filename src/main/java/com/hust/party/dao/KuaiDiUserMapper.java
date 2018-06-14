package com.hust.party.dao;

import com.hust.party.pojo.KuaiDiUser;
import com.hust.party.vo.UserInfoVO;
import org.springframework.stereotype.Repository;

@Repository
public interface KuaiDiUserMapper extends BaseMapper<KuaiDiUser>{

    Integer selectUserByOpenId(String open_id);

    KuaiDiUser selectUserInfo(Integer uid);
}