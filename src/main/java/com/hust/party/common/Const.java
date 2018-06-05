package com.hust.party.common;

/**
 * 存放各种常量信息<br/>
 * fan 2018/5/13 18:52
 */
public class Const {
    //order_user表
    public static final int ORDER_USER_STATUS_CANSEL = 0;    //订单取消
    public static final int ORDER_USER_STATUS_ACTIVATE = 1;    //订单拼单人数未达活动最小人数,也就是正在拼团的状态

    //order表
    public static final int ORDER_STATUS_CANCEL = 0;    //订单取消
    public static final int ORDER_STATUS_ENGAGING = 1;    //订单正在拼团的状态，并且还没有达到最小人数
    public static final int ORDER_STATUS_REACH_LEAST_PEOPLE = 2;    //订单达到最小人数，可以支付
    public static final int ORDER_STATUS_EXCEED_TIME = 3;    //订单超时，并且未消费
    public static final int ORDER_STATUS_HAS_FULL = 4;    //人数已满
    public static final int ORDER_STATUS_HAS_CONSUME = 5;    //订单已消费
    public static final int ORDER_STATUS_ENTERPRISE_CANCEL = 6;    //商家取消订单

    //订单列表相关，只用于标记，不是数据库中的值（全部，拼单中，待消费，已完成，退款中）
    public static final int ORDER_LIST_STATUS_ALL = 100;     //列出所有状态的订单
    public static final int ORDER_LIST_STATUS_ENGAGING = 101;     //列出拼单中的订单
    public static final int ORDER_LIST_STATUS_WAIT_CONSUME = 102;     //列出等待消费的订单
    public static final int ORDER_LIST_STATUS_FINISH = 103;     //列出已完成的订单
    public static final int ORDER_LIST_STATUS_DRAWBACKING = 104;     //列出退款中的订单

    
    //user_money表1.未转商户账户 2.已转商户账户 3.已退款
    public static final int USER_MONEY_NOT_TRANSFER_TO_ENTRERPRISE = 1;     //未转商户账户
    public static final int USER_MONEY_HAS_TRANSFER_TO_ENTRERPRISE = 2;     //已转商户账户
    public static final int USER_MONEY_DRAWBACK = 3;     //已退款

    //payment表
    public static final int PAYMENT_NOT_PAY = 0;    //未付
}