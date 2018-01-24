package com.jiacer.modules.business.bean.form;

import java.io.Serializable;

public class CourseBaseInfoForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 课程名称
	 */
	private String courseName;
	
	/**
	 * 课程ID
	 */
	private Integer courseId;
	
	/**
	 * 证书名称
	 */
	private String certName;
	
	/**
	 * 鉴定等级
	 */
	private String authenticateGrade;
	
	/**
	 * 总课时
	 */
	private Integer totalHours;
	
	/**
	 * 考试形式
	 */
	private String  examType;
	
	/**
	 * 发证机构
	 */
	private Integer  authorityId;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 课程状态
	 */
	private String status;

	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getAuthenticateGrade() {
		return authenticateGrade;
	}

	public void setAuthenticateGrade(String authenticateGrade) {
		this.authenticateGrade = authenticateGrade;
	}

	public Integer getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	
	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "CourseBaseInfoForm [courseName=" + courseName + ", courseId=" + courseId + ", certName=" + certName
				+ ", authenticateGrade=" + authenticateGrade + ", totalHours=" + totalHours + ", examType=" + examType
				+ ", authorityId=" + authorityId + ", remark=" + remark + ", status=" + status + "]";
	}

	
}
