package com.jiacer.modules.mybatis.entity;

import java.util.Date;

import com.jiacer.modules.mybatis.model.CertAuthority;
import com.jiacer.modules.mybatis.model.UserScores;
import com.jiacer.modules.system.config.DBStatus;

/** 
* @ClassName: UserScoresEntity 
* @Description: 学员成绩表
* @author 贺章鹏
* @date 2016年10月21日 下午4:45:12 
*  
*/
public class UserScoresEntity extends UserScores{

	private static final long serialVersionUID = 1L;
	
	private UserBaseInfoEntity userInfo;
	
	private UserExtendInfoEntity userExtend;
	
	private ExamClassEntity examClass;
	
	private LearnTypesEntity learnTypes;
	
	private SchoolsEntity schools;
	
	private CertAuthority certAuthority;//发证机构

	private UserCertEntity userCert;
	
	private String examResultName;
	
	/***************用于查询***********************/
	private Date startDate;//开始时间
	
	private Date endDate;//结束时间
	
	private String mobile;//学员手机号
	
	private Integer  schoolId;//学校名称 
	
	private Integer courseId;//报名课程
	
	private String userName;//学员姓名

	public UserBaseInfoEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBaseInfoEntity userInfo) {
		this.userInfo = userInfo;
	}

	public UserCertEntity getUserCert() {
		return userCert;
	}

	public void setUserCert(UserCertEntity userCert) {
		this.userCert = userCert;
	}

	public UserExtendInfoEntity getUserExtend() {
		return userExtend;
	}

	public void setUserExtend(UserExtendInfoEntity userExtend) {
		this.userExtend = userExtend;
	}

	public String getExamResultName() {
		return DBStatus.ExamResult.getDesc(super.getExamResult());
	}

	public void setExamResultName(String examResultName) {
		this.examResultName = examResultName;
	}

	public ExamClassEntity getExamClass() {
		return examClass;
	}

	public void setExamClass(ExamClassEntity examClass) {
		this.examClass = examClass;
	}

	public LearnTypesEntity getLearnTypes() {
		return learnTypes;
	}

	public void setLearnTypes(LearnTypesEntity learnTypes) {
		this.learnTypes = learnTypes;
	}

	public SchoolsEntity getSchools() {
		return schools;
	}

	public void setSchools(SchoolsEntity schools) {
		this.schools = schools;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public CertAuthority getCertAuthority() {
		return certAuthority;
	}

	public void setCertAuthority(CertAuthority certAuthority) {
		this.certAuthority = certAuthority;
	}
	
}
