package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.mybatis.model.UserBaseInfo;
import com.jiacer.modules.system.utils.DictionaryUtils;

/**
 * 
* @ClassName: UserBaseInfoEntity 
* @Description: 用户基础信息表
* @author 贺章鹏
* @date 2016年10月18日 下午12:09:50 
*
 */
public class UserBaseInfoEntity extends UserBaseInfo{

	private static final long serialVersionUID = 1L;
	
	private Integer couresCount;//课程数
	
	private UserExtendInfoEntity userExtend;

	
	
	/***************用户中文展示*******************/
	@SuppressWarnings("unused")
	private String isInviteName;//是否被邀请中文
	@SuppressWarnings("unused")
	private String authStatusName;//认证状态中文
	
	
	
	
	
	public String getIsInviteName() {
		if (super.getIsInvite()!=null&&!super.getIsInvite().equals("")) {
			return DictionaryUtils.getReSign(super.getIsInvite());
		}else {
			return isInviteName;
		}
		
	}

	public void setIsInviteName(String isInviteName) {
		this.isInviteName = isInviteName;
	}

	public String getAuthStatusName() {
		if (super.getAuthStatus()!=null && !super.getAuthStatus().equals("")) {
			return DictionaryUtils.getAuthStatus(super.getAuthStatus());
		}else {
			return authStatusName;
		}
	}

	public void setAuthStatusName(String authStatusName) {
		this.authStatusName = authStatusName;
	}

	public Integer getCouresCount() {
		return couresCount;
	}

	public void setCouresCount(Integer couresCount) {
		this.couresCount = couresCount;
	}

	public UserExtendInfoEntity getUserExtend() {
		return userExtend;
	}

	public void setUserExtend(UserExtendInfoEntity userExtend) {
		this.userExtend = userExtend;
	}

}
