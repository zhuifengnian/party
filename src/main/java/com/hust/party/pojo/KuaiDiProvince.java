package com.hust.party.pojo;

public class KuaiDiProvince {
    private Integer id;

    private String province;

    private String provinceName;

    public KuaiDiProvince(Integer id, String province, String provinceName) {
        this.id = id;
        this.province = province;
        this.provinceName = provinceName;
    }

    public KuaiDiProvince() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }
}