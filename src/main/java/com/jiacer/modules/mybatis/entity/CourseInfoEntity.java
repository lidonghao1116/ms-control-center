package com.jiacer.modules.mybatis.entity;


import java.io.Serializable;

import com.jiacer.modules.mybatis.model.CourseInfo;
import com.jiacer.modules.system.utils.DictionaryUtils;

public class CourseInfoEntity extends CourseInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private TemplateInfoEntity templateInfo;
	
	private TemplateInfoEntity templateInfoTf;//判断题
	
	private TemplateInfoEntity templateInfoMc;//多选题
	
	private TemplateInfoEntity templateInfoSc;//单选题
	
	/*************用于查询条件**************/
	private String  courseName;
	
	
	/*************用于显示中文名称**************/
	@SuppressWarnings("unused")
	private String statusName;
	@SuppressWarnings("unused")
	private String courseTypeName;
	@SuppressWarnings("unused")
	private String reSignName;
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getStatusName() {
		return  DictionaryUtils.getCoursStatus(super.getStatus());
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCourseTypeName() {
		return DictionaryUtils.getCoursType(super.getCourseType());
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public String getReSignName() {
		return DictionaryUtils.getReSign(super.getReSign());
	}

	public void setReSignName(String reSignName) {
		this.reSignName = reSignName;
	}

	public TemplateInfoEntity getTemplateInfo() {
		return templateInfo;
	}

	public void setTemplateInfo(TemplateInfoEntity templateInfo) {
		this.templateInfo = templateInfo;
	}

	public TemplateInfoEntity getTemplateInfoTf() {
		return templateInfoTf;
	}

	public void setTemplateInfoTf(TemplateInfoEntity templateInfoTf) {
		this.templateInfoTf = templateInfoTf;
	}

	public TemplateInfoEntity getTemplateInfoMc() {
		return templateInfoMc;
	}

	public void setTemplateInfoMc(TemplateInfoEntity templateInfoMc) {
		this.templateInfoMc = templateInfoMc;
	}

	public TemplateInfoEntity getTemplateInfoSc() {
		return templateInfoSc;
	}

	public void setTemplateInfoSc(TemplateInfoEntity templateInfoSc) {
		this.templateInfoSc = templateInfoSc;
	}
	
	
}
