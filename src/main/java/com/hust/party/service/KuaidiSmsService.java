package com.hust.party.service;

/**
 * 歪果仁取快递短信service<br/>
 * fan 2018/6/13 18:34
 */
public interface KuaidiSmsService {
    String extractExpressCode(String msg);
}