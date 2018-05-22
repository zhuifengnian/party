package com.hust.party.service;

import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Enterprise;

import java.util.List;

/**
 * <br/>
 * fan 2018/5/14 15:12
 */
public interface EnterpriseService extends BaseService<Enterprise>{
    Integer selectUserByChatId(String chat_id);
}