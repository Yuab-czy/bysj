package com.mindskip.xzs.repository;

import com.mindskip.xzs.domain.Classroom;
import com.mindskip.xzs.viewmodel.admin.education.ClassroomPageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassroomMapper extends BaseMapper<Classroom> {

    Classroom findByClassName(String name);
    List<Classroom> getClassroomByName(String Name);

    List<Classroom> allClassroom();

    List<Classroom> page(ClassroomPageRequestVM requestVM);
}
