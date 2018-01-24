package com.jiacer.modules.business.bean.form;

import java.util.Date;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: UsersQuery 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月11日 下午2:25:32 
*  
*/
public class UsersQuery implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private String userName;//用户姓名
	
	private Date startDate; //注册左时间
	
	private Date endDate; //注册右时间

	private String inviteCode;//邀请码
	
	private String mobile;//用户手机号
	
	private String authStatus;//用户状态
	
	
	
	
	
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
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

}
