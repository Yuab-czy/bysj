package com.mindskip.xzs.controller.admin;
import com.github.pagehelper.PageInfo;
import com.mindskip.xzs.base.BaseApiController;
import com.mindskip.xzs.base.RestResponse;
import com.mindskip.xzs.domain.Attendance;
import com.mindskip.xzs.service.AttendanceService;
import com.mindskip.xzs.utility.PageInfoHelper;
import com.mindskip.xzs.viewmodel.admin.attendance.AttendancePageRequestVM;
import com.mindskip.xzs.viewmodel.admin.attendance.AttendanceResponseVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang3.BooleanUtils.toInteger;

@RestController("AdminAttendanceController")
@RequestMapping(value = "/api/admin/attendance")
public class AttendanceController extends BaseApiController {

    private AttendanceService attendanceService;

    @Autowired
    public void setAttendanceService(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<AttendanceResponseVM>> pageList(@RequestBody AttendancePageRequestVM model) {
        PageInfo<Attendance> pageInfo = attendanceService.page(model);
        PageInfo<AttendanceResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> modelMapper.map(e, AttendanceResponseVM.class));
        return RestResponse.ok(page);
    }

    @RequestMapping(value= "/changeStatus", method = RequestMethod.POST)
    public boolean changeStatus(@RequestBody Integer id) {
        attendanceService.changeStatusById(id);
        return true;
    }


}
