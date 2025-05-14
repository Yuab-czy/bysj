package com.mindskip.xzs.viewmodel.admin.attendance;

import com.mindskip.xzs.viewmodel.BaseVM;

import java.sql.Timestamp;


public class AttendanceResponseVM extends BaseVM {
    private Integer id;

    private String classroomName;

    private String userName;

    private Timestamp startTime;

    private Timestamp endTime;

    private Integer aStatus;

    public Integer getaStatus() {
        return aStatus;
    }

    public void setaStatus(Integer aStatus) {
        this.aStatus = aStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
