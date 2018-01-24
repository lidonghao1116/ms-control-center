package com.jiacer.modules.mybatis.entity;

import java.util.Date;
import java.util.List;
import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.business.utils.LearnTypeUtil;
import com.jiacer.modules.business.utils.ProductsUtils;
import com.jiacer.modules.mybatis.model.ApplyOrders;
import com.jiacer.modules.mybatis.model.LearnTypes;

/**
 * 
* @ClassName: ApplyOrdersEntity 
* @Description: 申请报班订单表
* @author 贺章鹏
* @date 2016年10月18日 下午12:04:49 
*
 */
public class ApplyOrdersEntity extends ApplyOrders{

	private static final long serialVersionUID = 1L;
	
	private UserBaseInfoEntity userBaseInfo;//学员基本信息
	
	private UserExtendInfoEntity userExtendInfo;//学员身份信息
	
	private LearnTypesEntity learnTypes;//课程信息
	
	private SchoolsEntity schools;//学校信息
	
	private UserScoresEntity userScores;//用户成绩、考证信息

	private UserCertEntity userCert; //考证

	private ExamClassEntity examClass;//班级

	private List<ApplyOrdersEntity> orderList;//报考信息

	private StudentInfo student;

	private String orgName;

	private Integer studentId;

	public StudentInfo getStudent() {
		return student;
	}

	public void setStudent(StudentInfo student) {
		this.student = student;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/***************用于查询***********************/
	private Date startDate;//开始时间
	
	private Date endDate;//结束时间
	
	private String mobile;//学员手机号
	
	private Integer  schoolId;//学校名称 
	
	private Integer courseId;//报名课程
	
	private String userName;//学员姓名
	/***************用户中文展示*******************/
	@SuppressWarnings("unused")
	private String courseName;//学校管理系统特色课程

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCourseName() {
		System.out.println(this.getCourseId());
		return LearnTypeUtil.getName(this.getCourseId());
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public UserBaseInfoEntity getUserBaseInfo() {
		return userBaseInfo;
	}

	public void setUserBaseInfo(UserBaseInfoEntity userBaseInfo) {
		this.userBaseInfo = userBaseInfo;
	}

	public UserExtendInfoEntity getUserExtendInfo() {
		return userExtendInfo;
	}

	public void setUserExtendInfo(UserExtendInfoEntity userExtendInfo) {
		this.userExtendInfo = userExtendInfo;
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

	public UserCertEntity getUserCert() {
		return userCert;
	}

	public void setUserCert(UserCertEntity userCert) {
		this.userCert = userCert;
	}

	public List<ApplyOrdersEntity> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<ApplyOrdersEntity> orderList) {
		this.orderList = orderList;
	}


	public UserScoresEntity getUserScores() {
		return userScores;
	}

	public void setUserScores(UserScoresEntity userScores) {
		this.userScores = userScores;
	}

	

	public ExamClassEntity getExamClass() {
		return examClass;
	}

	public void setExamClass(ExamClassEntity examClass) {
		this.examClass = examClass;
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

	public SchoolsEntity getSchools() {
		return schools;
	}

	public void setSchools(SchoolsEntity schools) {
		this.schools = schools;
	}

	public LearnTypesEntity getLearnTypes() {
		return learnTypes;
	}

	public void setLearnTypes(LearnTypesEntity learnTypes) {
		this.learnTypes = learnTypes;
	}
}
