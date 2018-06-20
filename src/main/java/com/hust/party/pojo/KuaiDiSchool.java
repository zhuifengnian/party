package com.hust.party.pojo;

public class KuaiDiSchool {
    private Integer id;

    private String school;

    private Integer state;

    public KuaiDiSchool(Integer id, String school, Integer state) {
        this.id = id;
        this.school = school;
        this.state = state;
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
}