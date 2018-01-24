package com.jiacer.modules.business.validate;

import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.AnswersEntity;
import com.jiacer.modules.mybatis.entity.CourseInfoEntity;
import com.jiacer.modules.mybatis.entity.QuestionsEntity;
import com.jiacer.modules.mybatis.entity.TemplateInfoEntity;
import com.jiacer.modules.mybatis.model.CourseInfo;

/**
 *@Description: 
 *
 *@author: zhangsq
 *@date:   2017年5月26日 下午5:02:51
 * 
 */
public class QuestionAnswerValidate {

	/**
	 * 新增方法参数校验
	 * @return
	 */
	public static JsonResult addValidate(QuestionsEntity questions){
		AnswersEntity answersEntity = questions.getAnswers();
		 
		if (questions.getCourseId() == null) {
			return new JsonResult(false,"请选择所属课程",null);
		}
		if (questions.getQuestionType() == null || questions.getQuestionType().equals("")) {
			return new JsonResult(false,"请选择题目类型",null);
		}
		if (StringUtils.isEmpty(questions.getQuestion())) {
			return new JsonResult(false,"请填写题目名称",null);
		}
		if (questions.getStatus()==null||questions.getStatus().equals("")) {
			return new JsonResult(false,"请选择是否上下架",null);
		}
		//判断提
		if (questions.getQuestionType()!=null&&questions.getQuestionType().equals("02")) {
			if (StringUtils.isEmpty(answersEntity.getAnswerDesc1())) {
				return new JsonResult(false,"请填写判断题选项(注：对、错)",null);
			}
			if (StringUtils.isEmpty(answersEntity.getAnswerDesc2())) {
				return new JsonResult(false,"请填写判断题选项(注：对、错)",null);
			}
		}
		return new JsonResult(true,null,null);
	}
	
	/**
	 * 修改方法参数校验
	 * @return
	 */
	public static JsonResult modifyValidate(QuestionsEntity questions){
		if (StringUtils.isEmpty(questions.getQuestion())) {
			return new JsonResult(false,"请填写题目名称",null);
		}
		
		
		return new JsonResult(true,null,null);
	}

}
