package com.jiacer.modules.mybatis.entity;

import java.util.Date;
import java.util.List;

import com.jiacer.modules.mybatis.model.CertAuthority;
import com.jiacer.modules.mybatis.model.LearnTypes;
import com.jiacer.modules.system.utils.DictionaryUtils;

public class LearnTypesEntity extends LearnTypes{

	private static final long serialVersionUID = 1L;


	private SchoolsEntity schools;
	
	private CertAuthority certAuthority;

	private String orgName;
	/*************用于查询条件**************/
	private Date startDate;
	
	private Date endDate;
	/***********中文显示字段*****************/
	@SuppressWarnings("unused")
	private String examTypeName;//课程名称
	
	@SuppressWarnings("unused")
	private String statusName;//课程状态

	@SuppressWarnings("unused")
	private String authenticateGradeName;//鉴定等级

	private List<ClassTimesTemplate> classTimesList;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<ClassTimesTemplate> getClassTimesList() {
		return classTimesList;
	}

	public void setClassTimesList(List<ClassTimesTemplate> classTimesList) {
		this.classTimesList = classTimesList;
	}

	public String getExamTypeName() {
		return DictionaryUtils.getExamType(super.getExamType());
	}

	public void setExamTypeName(String examTypeName) {
		this.examTypeName = examTypeName;
	}

	public String getStatusName() {
		return DictionaryUtils.getCourseStatus(super.getStatus());
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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

	public SchoolsEntity getSchools() {
		return schools;
	}

	public void setSchools(SchoolsEntity schools) {
		this.schools = schools;
	}

	public CertAuthority getCertAuthority() {
		return certAuthority;
	}

	public void setCertAuthority(CertAuthority certAuthority) {
		this.certAuthority = certAuthority;
	}

	public String getAuthenticateGradeName() {
		return DictionaryUtils.getAuthenticateGrade(super.getAuthenticateGrade());
	}

	public void setAuthenticateGradeName(String authenticateGradeName) {
		this.authenticateGradeName = authenticateGradeName;
	}
	
}
