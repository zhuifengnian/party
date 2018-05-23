package com.hust.party.pojo;

import java.math.BigDecimal;

public class EnterprisePayment {
    private Integer id;

    private Integer enterpriseId;

    private BigDecimal accountMoney;

    private BigDecimal totalMoney;

    public EnterprisePayment(Integer id, Integer enterpriseId, BigDecimal accountMoney, BigDecimal totalMoney) {
        this.id = id;
        this.enterpriseId = enterpriseId;
        this.accountMoney = accountMoney;
        this.totalMoney = totalMoney;
    }

    public EnterprisePayment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
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