package com.hust.party.vo;

import java.math.BigDecimal;

/**
 * 企业数据信息vo<br/>
 * 李越 2018/6/11 11:33
 */
public class EnterpriseInfoVO {
    private String name;

    private String avatarurl;

    private BigDecimal accountMoney;

    private BigDecimal totalMoney;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public BigDecimal getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(BigDecimal accountMoney) {
        this.accountMoney = accountMoney;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}