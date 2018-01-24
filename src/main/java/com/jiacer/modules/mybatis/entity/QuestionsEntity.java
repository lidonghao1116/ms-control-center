package com.jiacer.modules.mybatis.entity;

import java.io.Serializable;

import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.mybatis.model.Questions;
import com.jiacer.modules.system.utils.DictionaryUtils;

/**
 * 
* @ClassName: QuestionsEntity 
* @Description: 题库表
* @author 贺章鹏
* @date 2016年10月18日 下午12:08:09 
*
 */
public class QuestionsEntity extends Questions  implements Serializable{

	private static final long serialVersionUID = 1L;

	private AnswersEntity answers;
	
	
	
	/*************用于显示中文名称**************/
	@SuppressWarnings("unused")
	private String courseName;
	@SuppressWarnings("unused")
	private String statusName;
	@SuppressWarnings("unused")
	private String questionTypeName;
	
	public String getStatusName() {
		return DictionaryUtils.getCoursStatus(super.getStatus());
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getQuestionTypeName() {
		return DictionaryUtils.getQuestionType(super.getQuestionType());
	}
	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}
	public String getCourseName() {
		return CoursesUtils.getName(super.getCourseId());
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public AnswersEntity getAnswers() {
		return answers;
	}
	public void setAnswers(AnswersEntity answers) {
		this.answers = answers;
	}
	/*public Integer getAnswerId1() {
		return answerId1;
	}
	public void setAnswerId1(Integer answerId1) {
		this.answerId1 = answerId1;
	}
	public Integer getAnswerId2() {
		return answerId2;
	}
	public void setAnswerId2(Integer answerId2) {
		this.answerId2 = answerId2;
	}
	public Integer getAnswerId3() {
		return answerId3;
	}
	public void setAnswerId3(Integer answerId3) {
		this.answerId3 = answerId3;
	}
	public Integer getAnswerId4() {
		return answerId4;
	}
	public void setAnswerId4(Integer answerId4) {
		this.answerId4 = answerId4;
	}
	public String getIsAnswer1() {
		return isAnswer1;
	}
	public void setIsAnswer1(String isAnswer1) {
		this.isAnswer1 = isAnswer1;
	}
	public String getIsAnswer2() {
		return isAnswer2;
	}
	public void setIsAnswer2(String isAnswer2) {
		this.isAnswer2 = isAnswer2;
	}
	public String getIsAnswer3() {
		return isAnswer3;
	}
	public void setIsAnswer3(String isAnswer3) {
		this.isAnswer3 = isAnswer3;
	}
	public String getIsAnswer4() {
		return isAnswer4;
	}
	public void setIsAnswer4(String isAnswer4) {
		this.isAnswer4 = isAnswer4;
	}
	public String getAnswerDesc1() {
		return answerDesc1;
	}
	public void setAnswerDesc1(String answerDesc1) {
		this.answerDesc1 = answerDesc1;
	}
	public String getAnswerDesc2() {
		return answerDesc2;
	}
	public void setAnswerDesc2(String answerDesc2) {
		this.answerDesc2 = answerDesc2;
	}
	public String getAnswerDesc3() {
		return answerDesc3;
	}
	public void setAnswerDesc3(String answerDesc3) {
		this.answerDesc3 = answerDesc3;
	}
	public String getAnswerDesc4() {
		return answerDesc4;
	}
	public void setAnswerDesc4(String answerDesc4) {
		this.answerDesc4 = answerDesc4;
	}
	public Integer getAnswerId5() {
		return answerId5;
	}
	public void setAnswerId5(Integer answerId5) {
		this.answerId5 = answerId5;
	}
	public String getIsAnswer5() {
		return isAnswer5;
	}
	public void setIsAnswer5(String isAnswer5) {
		this.isAnswer5 = isAnswer5;
	}
	public String getAnswerDesc5() {
		return answerDesc5;
	}
	public void setAnswerDesc5(String answerDesc5) {
		this.answerDesc5 = answerDesc5;
	}
	*/
}
