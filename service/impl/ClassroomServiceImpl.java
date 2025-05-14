package com.mindskip.xzs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mindskip.xzs.domain.Classroom;
import com.mindskip.xzs.repository.ClassroomMapper;
import com.mindskip.xzs.service.ClassroomService;
import com.mindskip.xzs.viewmodel.admin.education.ClassroomPageRequestVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl extends BaseServiceImpl<Classroom> implements ClassroomService {

    private final ClassroomMapper classroomMapper;

    @Autowired
    public ClassroomServiceImpl(ClassroomMapper classroomMapper) {
        super(classroomMapper);
        this.classroomMapper = classroomMapper;
    }


    @Override
    public List<Classroom> getClassroomByName(String name){ return classroomMapper.getClassroomByName(name);};

    @Override
    public List<Classroom> allClassroom(){ return classroomMapper.allClassroom(); };

    @Override
    public Classroom findByClassName(String name){ return  classroomMapper.findByClassName(name); };

    @Override
    public PageInfo<Classroom> page(ClassroomPageRequestVM requestVM){
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                classroomMapper.page(requestVM)
        );
    };

}
