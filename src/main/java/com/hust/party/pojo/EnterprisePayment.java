package com.hust.party.pojo;

public class EnterprisePayment {
    private Integer id;

    private Integer enterpriseId;

    private Integer accountMoney;

    private Integer totalMoney;

    public EnterprisePayment(Integer id, Integer enterpriseId, Integer accountMoney, Integer totalMoney) {
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

    public Integer getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Integer accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }
}