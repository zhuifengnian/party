package com.hust.party.common;

/**
 * 存放各种常量信息<br/>
 * fan 2018/5/13 18:52
 */
public class Const {
    public static final int ORDER_STATUS_CAN_PAY = 0;    //订单可以付款
    public static final int ORDER_STATUS_HAS_FULL = 1;    //订单拼单人数已满
    public static final int ORDER_STATUS_NOT_FULL = 2;    //订单拼单人数未满
    public static final int ORDER_STATUS_EXCEED_TIME = 3;    //订单日期已过
}