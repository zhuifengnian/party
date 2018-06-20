package com.hust.party.pojo;

public class KuaiDiSchool {
    private Integer id;

    private String school;

    private Integer state;

    private String schoolEnglish;

    private Integer provinceId;

    public KuaiDiSchool(Integer id, String school, Integer state, String schoolEnglish, Integer provinceId) {
        this.id = id;
        this.school = school;
        this.state = state;
        this.schoolEnglish = schoolEnglish;
        this.provinceId = provinceId;
    }

    public KuaiDiSchool() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
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
        this.schoolEnglish = schoolEnglish == null ? null : schoolEnglish.trim();
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}