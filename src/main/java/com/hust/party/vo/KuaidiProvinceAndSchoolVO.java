package com.hust.party.vo;

/**
 * 快递省份和学校vo<br/>
 * fan 2018/6/26 14:49
 */
public class KuaidiProvinceAndSchoolVO {
    private Integer provinceId;

    private String province;

    private String provinceName;

    private Integer schoolId;

    private String school;

    private Integer state;

    private String schoolEnglish;

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSchoolEnglish() {
        return schoolEnglish;
    }

    public void setSchoolEnglish(String schoolEnglish) {
        this.schoolEnglish = schoolEnglish;
    }
}