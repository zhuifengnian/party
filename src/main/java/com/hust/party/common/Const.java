package com.hust.party.common;

/**
 * 存放各种常量信息<br/>
 * fan 2018/5/13 18:52
 */
public class Const {
    public static final int ORDER_USER_STATUS_CANSEL = 0;    //订单取消
    public static final int ORDER_USER_STATUS_NOT_FULL = 1;    //订单拼单人数未达活动最小人数
    public static final int ORDER_USER_STATUS_SUCCESS = 2;    //拼团成功
    public static final int ORDER_USER_STATUS_CAN_PAY = 3;    //可以支付
    public static final int ORDER_USER_STATUS_EXCEED_TIME = 4;    //订单超时

    public static final int ORDER_STATUS_CANCEL = 0;    //订单取消
    public static final int ORDER_STATUS_ENGAGING = 1;    //正在拼团
    public static final int ORDER_STATUS_HAS_FULL = 2;    //人数已满
    public static final int ORDER_STATUS_EXCEED_TIME = 3;    //订单超时


}