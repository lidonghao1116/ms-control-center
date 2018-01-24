package com.jiacer.modules.business.controller;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.business.service.QuestionService;
import com.jiacer.modules.business.validate.CoursesValidate;
import com.jiacer.modules.business.validate.QuestionAnswerValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.AnswersEntity;
import com.jiacer.modules.mybatis.entity.CourseInfoEntity;
import com.jiacer.modules.mybatis.entity.QuestionsEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;

/**
 * @Description: 题库管理控制器
 * @author: zhangsq
 * @date: 2017年5月25日 下午5:51:33
 */
@Controller
@RequestMapping(MappingURL.QUESTION_URL)
public class QuestionsController extends BaseController {


    @Resource
    QuestionService questionService;

    //列表页面
    @RequestMapping(MappingURL.LIST_URL)
    public String list(Model model) {
        return "modules/question/list";
    }

    public List getModel(Integer id) {
        if (id != null) {

            return questionService.getById(id);

        } else {
            return null;
        }
    }

    //新增修改页面
    @RequestMapping(MappingURL.FORM_URL)
    public String form(Model model, Integer id) {

        //QuestionsEntity questionsEntity=this.getModel(id);
        List list = this.getModel(id);
        if (id == null) {
            model.addAttribute("update", Boolean.FALSE);
            model.addAttribute("model", null);
        } else {
            model.addAttribute("update", Boolean.TRUE);
            //model.addAttribute("model", questionsEntity);
            model.addAttribute("model", list);
        }
        return "modules/question/form";
    }

    //查询
    @RequestMapping(MappingURL.QUERY_URL)
    @ResponseBody
    public Page<QuestionsEntity> page(
            @RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
            @RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
            Model model, QuestionsEntity questionsEntity) {
        Page<QuestionsEntity> reslut = questionService.getQuestionPage(questionsEntity, pageNumber, pageSize);
        return reslut;
    }

    //新增
    @RequestMapping(MappingURL.ADD_URL)
    @ResponseBody
    public JsonResult add(Model model, @RequestBody QuestionsEntity questionsEntity) {
        if (questionsEntity == null) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "题库管理对象为null"));
            return new JsonResult(false, Message.PARAM_ERROR_MSG, null);
        }

        JsonResult validate = QuestionAnswerValidate.addValidate(questionsEntity);

        if (!validate.isSuccess()) {
            return validate;
        }

        JsonResult jsonResult = null;

        try {
            questionService.addquestion(questionsEntity);
            jsonResult = new JsonResult(true, Message.SUCCESS_MSG, null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION, e));
            jsonResult = new JsonResult(false, Message.FAILED_MSG, null);
        }
        return jsonResult;
    }

    //修改
    @RequestMapping(MappingURL.MODIFY_URL)
    @ResponseBody
    public JsonResult modify(Model model, @RequestBody QuestionsEntity questionsEntity) {
            /*if(answersEntity == null || answersEntity.getQuestionId() ==null){
				logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "题库管理对象为null或题库管理id为空"));
				return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
			}*/

        JsonResult validate = QuestionAnswerValidate.modifyValidate(questionsEntity);

        if (!validate.isSuccess()) {
            return validate;
        }

        JsonResult jsonResult = null;

        try {
            questionService.modifyquestion(questionsEntity);
            jsonResult = new JsonResult(true, Message.SUCCESS_MSG, null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION, e));
            jsonResult = new JsonResult(false, Message.FAILED_MSG, null);
        }
        return jsonResult;
    }


}
