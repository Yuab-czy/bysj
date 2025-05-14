package com.mindskip.xzs.viewmodel.student.classroom;

import com.mindskip.xzs.viewmodel.BaseVM;


public class ClassroomResponseVM extends BaseVM {
    private Integer id;

    private String name;

    private Integer hasNum;

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
