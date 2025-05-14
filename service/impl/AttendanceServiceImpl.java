package com.mindskip.xzs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mindskip.xzs.domain.Attendance;
import com.mindskip.xzs.repository.AttendanceMapper;
import com.mindskip.xzs.repository.ClassroomMapper;
import com.mindskip.xzs.service.AttendanceService;
import com.mindskip.xzs.viewmodel.admin.attendance.AttendancePageRequestVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AttendanceServiceImpl extends BaseServiceImpl<Attendance> implements AttendanceService {


    private final AttendanceMapper attendanceMapper;

    @Autowired
    public AttendanceServiceImpl(AttendanceMapper attendanceMapper) {
        super(attendanceMapper);
        this.attendanceMapper = attendanceMapper;
    }

    @Override
    public PageInfo<Attendance> page(AttendancePageRequestVM requestVM){
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                attendanceMapper.page(requestVM)
        );
    };

    public void changeStatusById(Integer id){
        attendanceMapper.changeStatusById(id);
    }

    public void insertSubmit(Attendance attendance){
        attendanceMapper.insertSubmit(attendance);
    }

}
