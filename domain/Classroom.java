package com.mindskip.xzs.domain;

import java.io.Serializable;

public class Classroom implements Serializable {


    private Integer id;

    /**
     * 教室名字
     */
    private String name;

    /**
     * 已经预约人数
     */
    private Integer hasNum;

    /**
     * 可容纳人数
     */
    private Integer totalNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHasNum() {
        return hasNum;
    }

    public void setHasNum(Integer hasNum) {
        this.hasNum = hasNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
