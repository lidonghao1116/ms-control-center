package com.jiacer.modules.mybatis.entity;

import java.io.Serializable;

import com.jiacer.modules.mybatis.model.CourseBaseInfo;

public class CourseBaseInfoEntity extends CourseBaseInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 证书名称
	 */
	private String certName;
	
	/**
	 * 发证机构
	 */
	private String authorityName;

	/**
	 * 鉴定等级
	 */
	private String orgName;
	
	/**
	 * 课程状态
	 */
	private String statusName;
	
	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "CourseBaseInfoEntity [certName=" + certName + ", authorityName=" + authorityName + ", orgName="
				+ orgName + ", statusName=" + statusName + "]";
	}

	
	
}
