package com.mindskip.xzs.controller.admin;


import com.mindskip.xzs.base.BaseApiController;
import com.mindskip.xzs.base.RestResponse;
import com.mindskip.xzs.domain.Classroom;
import com.mindskip.xzs.domain.Subject;
import com.mindskip.xzs.service.ClassroomService;
import com.mindskip.xzs.service.SubjectService;
import com.mindskip.xzs.utility.PageInfoHelper;
import com.mindskip.xzs.viewmodel.admin.education.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("AdminEducationController")
@RequestMapping(value = "/api/admin/education")
public class EducationController extends BaseApiController {

    private SubjectService subjectService;

    @Autowired
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    private ClassroomService classroomService;

    @Autowired
    public void setClassroomService(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }


    @RequestMapping(value = "/subject/list", method = RequestMethod.POST)
    public RestResponse<List<Subject>> list() {
        List<Subject> subjects = subjectService.allSubject();
        return RestResponse.ok(subjects);
    }

    @RequestMapping(value = "/classroom/list", method = RequestMethod.POST)
    public RestResponse<List<Classroom>> classroomList() {
        List<Classroom> classrooms = classroomService.allClassroom();
        return RestResponse.ok(classrooms);
    }

    @RequestMapping(value = "/subject/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<SubjectResponseVM>> pageList(@RequestBody SubjectPageRequestVM model) {
        PageInfo<Subject> pageInfo = subjectService.page(model);
        PageInfo<SubjectResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> modelMapper.map(e, SubjectResponseVM.class));
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/classroom/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<ClassroomResponseVM>> pageList(@RequestBody ClassroomPageRequestVM model) {
        PageInfo<Classroom> pageInfo = classroomService.page(model);
        PageInfo<ClassroomResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> modelMapper.map(e, ClassroomResponseVM.class));
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/subject/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody @Valid SubjectEditRequestVM model) {
        Subject subject = modelMapper.map(model, Subject.class);
        if (model.getId() == null) {
            subject.setDeleted(false);
            subjectService.insertByFilter(subject);
        } else {
            subjectService.updateByIdFilter(subject);
        }
        return RestResponse.ok();
    }

    @RequestMapping(value = "/classroom/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody @Valid ClassroomEditRequestVM model) {

        Classroom classroom = modelMapper.map(model, Classroom.class);
        System.out.println(classroom.getId());
        System.out.println(classroom.getHasNum());
        System.out.println(classroom.getTotalNum());
        if (model.getId() == null) {
            classroomService.insertByFilter(classroom);
        } else {
            classroomService.updateByIdFilter(classroom);
        }
        return RestResponse.ok();
    }

    @RequestMapping(value = "/subject/select/{id}", method = RequestMethod.POST)
    public RestResponse<SubjectEditRequestVM> select(@PathVariable Integer id) {
        Subject subject = subjectService.selectById(id);
        SubjectEditRequestVM vm = modelMapper.map(subject, SubjectEditRequestVM.class);
        return RestResponse.ok(vm);
    }

    @RequestMapping(value = "/subject/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        Subject subject = subjectService.selectById(id);
        subject.setDeleted(true);
        subjectService.updateById(subject);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/classroom/select/{id}", method = RequestMethod.POST)
    public RestResponse<ClassroomEditRequestVM> classroomSelect(@PathVariable Integer id) {
        Classroom classroom = classroomService.selectById(id);
        ClassroomEditRequestVM vm = modelMapper.map(classroom, ClassroomEditRequestVM.class);
        return RestResponse.ok(vm);
    }

    @RequestMapping(value = "/classroom/delete/{id}", method = RequestMethod.POST)
    public RestResponse classroomDelete(@PathVariable Integer id) {
        classroomService.deleteById(id);
        return RestResponse.ok();
    }
}
