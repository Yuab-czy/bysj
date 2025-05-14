package com.mindskip.xzs.service;


import com.github.pagehelper.PageInfo;
import com.mindskip.xzs.domain.Attendance;
import com.mindskip.xzs.repository.AttendanceMapper;
import com.mindskip.xzs.viewmodel.admin.attendance.AttendancePageRequestVM;

import java.util.Map;

public interface AttendanceService extends BaseService<Attendance>{

    void insertSubmit(Attendance attendance);

    PageInfo<Attendance> page(AttendancePageRequestVM requestVM);

    void changeStatusById(Integer id);
}
