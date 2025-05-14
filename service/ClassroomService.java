package com.mindskip.xzs.service;

import com.mindskip.xzs.domain.Classroom;
import com.mindskip.xzs.viewmodel.admin.education.ClassroomPageRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClassroomService extends BaseService<Classroom> {

    List<Classroom> getClassroomByName(String name);
    List<Classroom> allClassroom();

    Classroom findByClassName(String name);

    PageInfo<Classroom> page(ClassroomPageRequestVM requestVM);
}
