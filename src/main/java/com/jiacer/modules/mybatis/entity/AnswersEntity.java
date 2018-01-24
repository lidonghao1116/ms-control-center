package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.mybatis.model.Answers;

/**
 * 
* @ClassName: AnswersEntity 
* @Description: 问题答案表
* @author 贺章鹏
* @date 2016年10月18日 下午12:04:25 
*
 */
public class AnswersEntity extends Answers{

	private static final long serialVersionUID = 1L;

	//private QuestionsEntity questions;
	
	private Integer questionId; 
	private Integer answerId1; 
	private Integer answerId2; 
	private Integer answerId3; 
	private Integer answerId4; 
	private Integer answerId5; 
	private String  isAnswer1;
	private String  isAnswer2;
	private String  isAnswer3;
	private String  isAnswer4;
	private String  isAnswer5;
	private String  answerDesc1;
	private String  answerDesc2;
	private String  answerDesc3;
	private String  answerDesc4;
	private String  answerDesc5;
	
	public Integer getAnswerId1() {
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
/*	public QuestionsEntity getQuestions() {
		return questions;
	}
	public void setQuestions(QuestionsEntity questions) {
		this.questions = questions;
	}*/
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
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
	public Integer getAnswerId5() {
		return answerId5;
	}
	public void setAnswerId5(Integer answerId5) {
		this.answerId5 = answerId5;
	}
	
}
