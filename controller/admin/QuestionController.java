package com.mindskip.xzs.controller.admin;

import com.mindskip.xzs.base.BaseApiController;
import com.mindskip.xzs.base.RestResponse;
import com.mindskip.xzs.base.SystemCode;
import com.mindskip.xzs.domain.Question;
import com.mindskip.xzs.domain.TextContent;
import com.mindskip.xzs.domain.enums.QuestionTypeEnum;
import com.mindskip.xzs.domain.question.QuestionObject;
import com.mindskip.xzs.service.QuestionService;
import com.mindskip.xzs.service.TextContentService;
import com.mindskip.xzs.utility.*;
import com.mindskip.xzs.viewmodel.admin.question.QuestionEditRequestVM;
import com.mindskip.xzs.viewmodel.admin.question.QuestionPageRequestVM;
import com.mindskip.xzs.viewmodel.admin.question.QuestionResponseVM;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("AdminQuestionController")

@RequestMapping(value = "/api/admin/question")
public class QuestionController extends BaseApiController {
    private final QuestionService questionService;
    private final TextContentService textContentService;
    @Autowired
    public QuestionController(QuestionService questionService, TextContentService textContentService) {
        this.questionService = questionService;
        this.textContentService = textContentService;
    }
    //获取题目的分页列表
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<QuestionResponseVM>> pageList(@RequestBody QuestionPageRequestVM model) {
        PageInfo<Question> pageInfo = questionService.page(model);
        PageInfo<QuestionResponseVM> page = PageInfoHelper.copyMap(pageInfo, q -> {
            QuestionResponseVM vm = modelMapper.map(q, QuestionResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(q.getCreateTime()));
            vm.setScore(ExamUtil.scoreToVM(q.getScore()));
            TextContent textContent = textContentService.selectById(q.getInfoTextContentId());
            QuestionObject questionObject = JsonUtil.toJsonObject(textContent.getContent(), QuestionObject.class);
            String clearHtml = HtmlUtil.clear(questionObject.getTitleContent());
            vm.setShortTitle(clearHtml);
            return vm;
        });
        return RestResponse.ok(page);
    }
    //编辑是新增题目
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody @Valid QuestionEditRequestVM model) {
        RestResponse validQuestionEditRequestResult = validQuestionEditRequestVM(model);
        if (validQuestionEditRequestResult.getCode() != SystemCode.OK.getCode()) {
            return validQuestionEditRequestResult;
        }if (null == model.getId()) {questionService.insertFullQuestion(model, getCurrentUser().getId());
        } else {questionService.updateFullQuestion(model);
        }return RestResponse.ok();}
//根据题目ID获取题目详情
    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<QuestionEditRequestVM> select(@PathVariable Integer id) {
        QuestionEditRequestVM newVM = questionService.getQuestionEditRequestVM(id);
        return RestResponse.ok(newVM);
    }
//删除题目
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        Question question = questionService.selectById(id);
        question.setDeleted(true);
        questionService.updateByIdFilter(question);
        return RestResponse.ok();
    }

    private RestResponse validQuestionEditRequestVM(QuestionEditRequestVM model) {
        int qType = model.getQuestionType().intValue();
        boolean requireCorrect = qType == QuestionTypeEnum.SingleChoice.getCode() || qType == QuestionTypeEnum.TrueFalse.getCode();
        if (requireCorrect) {
            if (StringUtils.isBlank(model.getCorrect())) {
                String errorMsg = ErrorUtil.parameterErrorFormat("correct", "不能为空");
                return new RestResponse<>(SystemCode.ParameterValidError.getCode(), errorMsg);
            }
        }

        if (qType == QuestionTypeEnum.GapFilling.getCode()) {
            Integer fillSumScore = model.getItems().stream().mapToInt(d -> ExamUtil.scoreFromVM(d.getScore())).sum();
            Integer questionScore = ExamUtil.scoreFromVM(model.getScore());
            if (!fillSumScore.equals(questionScore)) {
                String errorMsg = ErrorUtil.parameterErrorFormat("score", "空分数和与题目总分不相等");
                return new RestResponse<>(SystemCode.ParameterValidError.getCode(), errorMsg);
            }
        }
        return RestResponse.ok();
    }
}
