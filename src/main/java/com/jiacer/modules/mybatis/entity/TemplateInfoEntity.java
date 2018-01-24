package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.mybatis.model.TemplateInfo;

public class TemplateInfoEntity extends TemplateInfo {

	private String tf;//判断题分数
	
	private String sc;//单选题分手
	
	private String mc;//多选题分数
	
	private String subjectCountTF;//判断题目总数
	
	private String subjectCountSC;//单选题目总数
	
	private String subjectCountMC;//多选题目总数
	
	public TemplateInfoEntity() {}
	public TemplateInfoEntity(String full) {
		this.tf="";
		this.sc="";
		this.mc="";
		this.subjectCountTF="";
		this.subjectCountSC="";
		this.subjectCountMC="";
	}

	public String getTf() {
		return tf;
	}

	public void setTf(String tf) {
		this.tf = tf;
	}

	public String getSc() {
		return sc;
	}

	public void setSc(String sc) {
		this.sc = sc;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getSubjectCountTF() {
		return subjectCountTF;
	}

	public void setSubjectCountTF(String subjectCountTF) {
		this.subjectCountTF = subjectCountTF;
	}

	public String getSubjectCountSC() {
		return subjectCountSC;
	}

	public void setSubjectCountSC(String subjectCountSC) {
		this.subjectCountSC = subjectCountSC;
	}

	public String getSubjectCountMC() {
		return subjectCountMC;
	}

	public void setSubjectCountMC(String subjectCountMC) {
		this.subjectCountMC = subjectCountMC;
	}
	
}
