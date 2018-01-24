package com.jiacer.modules.business.service;


import java.util.*;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.AnswersEntity;
import com.jiacer.modules.mybatis.entity.CourseInfoEntity;
import com.jiacer.modules.mybatis.entity.QuestionsEntity;

public interface QuestionService {

	/**
	 * 根据id获取题库信息
	 * @param id
	 * @return
	 */
	List getById(Integer id);
	
	/**
	 * 题库管理分页
	 * @param questionsEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<QuestionsEntity> getQuestionPage(QuestionsEntity questionsEntity, int pageNumber, int pageSize);
	
	/**
	 * 新增问题和答案
	 * @param answersEntity
	 * @throws Exception
	 */
	void addquestion(QuestionsEntity questionsEntity) throws Exception;

	/**
	 * 修改问题和答案
	 * @param answersEntity
	 * @throws Exception
	 */
	void modifyquestion(QuestionsEntity questionsEntity) throws Exception;

}
