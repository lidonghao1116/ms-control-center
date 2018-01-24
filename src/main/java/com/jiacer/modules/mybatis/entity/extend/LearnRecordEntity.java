package com.jiacer.modules.mybatis.entity.extend;

import java.math.BigDecimal;
import java.util.Dictionary;

import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.common.persistence.ModelSerializable;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.system.utils.DictionaryUtils;

/** 
* @ClassName: LearnRecordEntity 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月7日 下午3:50:49 
*  
*/
public class LearnRecordEntity implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private Integer userId;//用户id

	private Integer answersNum;//答题次数
	
	private Integer classId;//班级
	
	private BigDecimal scores;//得分
	
	private Integer courseId;//课程id
	@SuppressWarnings("unused")
	private String couresName;//课程名称
	
	private UserBaseInfoEntity userInfo;
	
	private UserExtendInfoEntity userExtend;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAnswersNum() {
		return answersNum;
	}

	public void setAnswersNum(Integer answersNum) {
		this.answersNum = answersNum;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public BigDecimal getScores() {
		return scores;
	}

	public void setScores(BigDecimal scores) {
		this.scores = scores;
	}

	public UserBaseInfoEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBaseInfoEntity userInfo) {
		this.userInfo = userInfo;
	}

	public UserExtendInfoEntity getUserExtend() {
		return userExtend;
	}

	public void setUserExtend(UserExtendInfoEntity userExtend) {
		this.userExtend = userExtend;
	}

	public String getCouresName() {
		return CoursesUtils.getName(this.getCourseId());
	}

	public void setCouresName(String couresName) {
		this.couresName = couresName;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}


}
