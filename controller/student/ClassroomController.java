package com.mindskip.xzs.controller.student;

import com.github.pagehelper.PageInfo;
import com.mindskip.xzs.base.BaseApiController;
import com.mindskip.xzs.base.RestResponse;
import com.mindskip.xzs.domain.Attendance;
import com.mindskip.xzs.domain.Classroom;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.repository.AttendanceMapper;
import com.mindskip.xzs.service.AttendanceService;
import com.mindskip.xzs.service.ClassroomService;
import com.mindskip.xzs.service.UserService;
import com.mindskip.xzs.utility.PageInfoHelper;
import com.mindskip.xzs.viewmodel.admin.attendance.AttendancePageRequestVM;
import com.mindskip.xzs.viewmodel.admin.attendance.AttendanceResponseVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController("StudentClassroomController")
@RequestMapping(value = "/api/student/classroom")
public class ClassroomController extends BaseApiController {


    private ClassroomService classroomService;

    @Autowired
    public void setClassroomService(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public RestResponse<List<Classroom>> classroomList() {
        List<Classroom> classrooms = classroomService.allClassroom();


        return RestResponse.ok(classrooms);
    }

    private UserService userService;

    @Autowired
    public void UserService(UserService userService) {
        this.userService = userService;
    }

    private AttendanceService attendanceService;


    @Autowired
    public void setAttendanceService(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public boolean SubmitAttendance(@RequestBody Map<String, Object> submitData) {
        User user = getCurrentUser();
        Attendance attendance = new Attendance();
        String userName = user.getRealName();
        String className = (String) submitData.get("className");
        String startDate = (String) submitData.get("startDate");
        String endDate = (String) submitData.get("endDate");
        Timestamp cStartTime = convertStringToTimestamp(startDate);
        Timestamp cEndTime = convertStringToTimestamp(endDate);

        // 根据 className 查询教室信息
        Classroom classroom = classroomService.findByClassName(className);

        // 如果教室信息不存在或者 hasNum >= totalNum，则抛出异常
        if (classroom == null || classroom.getHasNum() >= classroom.getTotalNum()) {
            throw new IllegalArgumentException("课堂不存在或已满");
        }

        attendance.setUserName(userName);
        attendance.setClassroomName(className);
        attendance.setStartTime(cStartTime);
        attendance.setEndTime(cEndTime);

        attendanceService.insertSubmit(attendance);
        return true;
    }

    public static Timestamp convertStringToTimestamp(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            Date date = sdf.parse(dateString);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // 返回 null 表示转换失败
        }
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
