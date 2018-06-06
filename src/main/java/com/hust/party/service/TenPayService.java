package com.hust.party.service;

import com.hust.party.wxpay.PrepayIdRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 微信支付业务层<br/>
 * fan 2018/6/5 16:55
 */
public interface TenPayService {
    Map<String, Object> getPrepayId(String openid, Integer aid, boolean isGenerator, HttpServletRequest request, HttpServletResponse response);
}