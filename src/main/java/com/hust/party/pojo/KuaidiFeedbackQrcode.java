package com.hust.party.pojo;

import java.util.Date;

/**
 * <br/>
 * fan 2018/7/5 17:51
 */
public class KuaidiFeedbackQrcode {
    private Integer id;
    private String qrcodeUrl;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}