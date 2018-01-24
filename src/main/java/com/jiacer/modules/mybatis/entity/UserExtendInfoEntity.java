package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.business.utils.PartnerUtils;
import com.jiacer.modules.mybatis.model.UserExtendInfo;
import com.jiacer.modules.system.utils.DictionaryUtils;

/**
 * 
* @ClassName: UserExtendInfoEntity 
* @Description: 用户信息扩展表
* @author 贺章鹏
* @date 2016年10月18日 下午12:10:04 
*
 */
public class UserExtendInfoEntity extends UserExtendInfo{

	private static final long serialVersionUID = 1L;
	
	/****************用户中文展示************************/
	@SuppressWarnings("unused")
	private String sourceTypeName;
	
	@SuppressWarnings("unused")
	private String sourceTypeSecName;

	@SuppressWarnings("unused")
	private String sourceValueName;
	
	@SuppressWarnings("unused")
	private String educationName;

	public String getSourceTypeName() {
		return DictionaryUtils.getSourceTypeName(super.getSourceType());
	}

	public void setSourceTypeName(String sourceTypeName) {
		this.sourceTypeName = sourceTypeName;
	}

	public String getSourceTypeSecName() {
		return DictionaryUtils.getSourceTypeSecName(super.getSourceType(), super.getSourceTypeSec());
	}

	public void setSourceTypeSecName(String sourceTypeSecName) {
		this.sourceTypeSecName = sourceTypeSecName;
	}

	public String getSourceValueName() {
		try {
			int partnerId=Integer.parseInt(super.getSourceValue());
			return PartnerUtils.getName(partnerId);
		} catch (Exception e) {
			return super.getSourceValue();
		}
	}

	public void setSourceValueName(String sourceValueName) {
		this.sourceValueName = sourceValueName;
	}

	public String getEducationName() {
		return DictionaryUtils.getEducationName(super.getEducation());
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}
	
}
