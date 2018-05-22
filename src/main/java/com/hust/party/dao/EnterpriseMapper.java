package com.hust.party.dao;

import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Enterprise;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnterpriseMapper  extends BaseMapper<Enterprise>{
    Integer selectUserByChatId(String chat_id);
}