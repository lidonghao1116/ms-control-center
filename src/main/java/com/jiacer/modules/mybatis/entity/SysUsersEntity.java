package com.jiacer.modules.mybatis.entity;

import java.util.Date;

import com.jiacer.modules.mybatis.model.SysUsers;
import com.jiacer.modules.system.config.DBStatus;



/**
 * 
* @ClassName: SysUsersEntity 
* @Description: 管理系统用户表
* @author 贺章鹏
* @date 2016年10月18日 下午12:08:40 
*
 */
public class SysUsersEntity extends SysUsers{

	private static final long serialVersionUID = 1L;
	
	/*public SysUsersEntity(){
		super();
		setIsUsable(DBStatus.IsUsable.TRUE);
		setAddTime(new Date());
	}*/
	private String inviteCode;//邀请码
	private String mobile;//用户手机号
	private String name;//用户姓名
	private String userStatus;//用户状态
	private Date startDate;//开始时间
	private Date endDate;//结束时间
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
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
