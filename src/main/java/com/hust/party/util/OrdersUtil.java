package com.hust.party.util;

import com.hust.party.common.Const;

/**
 * 订单工具类<br/>
 * fan 2018/6/8 11:54
 */
public class OrdersUtil {
    /**
     * 根据订单状态值返回状态名
     */
    public static String getStateName(Integer state){
        String ret = "";
        switch(state){
            case Const.ORDER_STATUS_CANCEL:
                ret = "已取消";
                break;
            case Const.ORDER_STATUS_ENGAGING:
                ret = "拼单中";
                break;
            case Const.ORDER_STATUS_REACH_LEAST_PEOPLE:
            case Const.ORDER_STATUS_HAS_FULL:   //人数已满和可以支付的状态都是待消费的状态
                ret = "待消费";
                break;
            case Const.ORDER_STATUS_EXCEED_TIME:
                ret = "已超时";
                break;
            case Const.ORDER_STATUS_HAS_CONSUME:
                ret = "已完成";
                break;
            case Const.ORDER_STATUS_ENTERPRISE_CANCEL:
                ret = "商家取消订单";
                break;
            default:
                ret = "未知订单状态";
        }
        return ret;
    }
}