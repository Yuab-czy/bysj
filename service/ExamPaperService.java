package com.mindskip.xzs.service;

import com.mindskip.xzs.domain.ExamPaper;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.mindskip.xzs.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.mindskip.xzs.viewmodel.student.dashboard.PaperFilter;
import com.mindskip.xzs.viewmodel.student.dashboard.PaperInfo;
import com.mindskip.xzs.viewmodel.student.exam.ExamPaperPageVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

// ---------------------- 管理考试试卷 ----------------------

public interface ExamPaperService extends BaseService<ExamPaper> {
// ---------------------- 分页查询考试试卷 ----------------------
    PageInfo<ExamPaper> page(ExamPaperPageRequestVM requestVM);
// ---------------------- 分页查询任务考试试卷 ----------------------
    PageInfo<ExamPaper> taskExamPage(ExamPaperPageRequestVM requestVM);
// ---------------------- 学生分页查询考试试卷 ----------------------
    PageInfo<ExamPaper> studentPage(ExamPaperPageVM requestVM);
// ---------------------- 保存或更新考试试卷 ----------------------
    ExamPaper savePaperFromVM(ExamPaperEditRequestVM examPaperEditRequestVM, User user);
// ---------------------- 将考试试卷转换为视图模型 ----------------------
    ExamPaperEditRequestVM examPaperToVM(Integer id);
// ---------------------- 根据过滤条件获取试卷信息 ----------------------
    List<PaperInfo> indexPaper(PaperFilter paperFilter);
// ---------------------- 获取所有试卷总数----------------------
    Integer selectAllCount();
// ---------------------- 获取每月试卷数量统计 ----------------------
    List<Integer> selectMothCount();
}
