package com.mindskip.xzs.service;

import com.mindskip.xzs.domain.Question;
import com.mindskip.xzs.viewmodel.admin.question.QuestionEditRequestVM;
import com.mindskip.xzs.viewmodel.admin.question.QuestionPageRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface QuestionService extends BaseService<Question> {
    PageInfo<Question> page(QuestionPageRequestVM requestVM);//用于分页查询题目
    Question insertFullQuestion(QuestionEditRequestVM model, Integer userId);//新增题目
    Question updateFullQuestion(QuestionEditRequestVM model);//更新完整题目信息
    QuestionEditRequestVM getQuestionEditRequestVM(Integer questionId);
    QuestionEditRequestVM getQuestionEditRequestVM(Question question);
    Integer selectAllCount();
    List<Integer> selectMothCount();
}
