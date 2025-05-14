package com.mindskip.xzs.repository;

import com.mindskip.xzs.domain.Attendance;
import com.mindskip.xzs.viewmodel.admin.attendance.AttendancePageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance> {

    void insertSubmit(Attendance attendance);

    List<Attendance> page(AttendancePageRequestVM requestVM);

    void changeStatusById(Integer id);
}
