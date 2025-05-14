package com.mindskip.xzs.viewmodel.admin.education;

import com.mindskip.xzs.viewmodel.BaseVM;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class ClassroomEditRequestVM extends BaseVM {

    private Integer id;

    @NotBlank
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
