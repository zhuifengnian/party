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
                ret = "正在拼团";
                break;
            case Const.ORDER_STATUS_REACH_LEAST_PEOPLE:
                ret = "可以支付";
                break;
            case Const.ORDER_STATUS_EXCEED_TIME:
                ret = "已超时";
                break;
            case Const.ORDER_STATUS_HAS_FULL:
                ret = "人数已满";
                break;
            case Const.ORDER_STATUS_HAS_CONSUME:
                ret = "已消费";
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