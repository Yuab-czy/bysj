package com.mindskip.xzs.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Attendance implements Serializable {

    private Integer id;

    /**
     * 教室名字
     */
    private String classroomName;

    /**
     * 预约的学生名字
     */
    private String userName;

    /**
     * 开始时间
     */
    private Timestamp startTime;

    /**
     * 结束时间
     */
    private Timestamp endTime;


    /**
     * 考勤状态
     */
    private Integer aStatus;



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

    public Integer getaStatus() {
        return aStatus;
    }

    public void setaStatus(Integer aStatus) {
        this.aStatus = aStatus;
    }
}
