package com.jiacer.modules.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

import com.jiacer.modules.mybatis.model.RedPacket;
import com.jiacer.modules.system.utils.DictionaryUtils;

public class RedPacketEntity extends RedPacket implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private UserExtendInfoEntity userExtendInfo;
	
	private UserBaseInfoEntity userBaseInfo;

	private Date startDate;
	
	private Date endDate;

	/*************用于显示中文名称**************/
	@SuppressWarnings("unused")
	private String statusName;//清算状态
	
	@SuppressWarnings("unused")
	private String payStatusName;//清算状态


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

	public String getStatusName() {
		return DictionaryUtils.getRedPacket(super.getStatus());
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public UserExtendInfoEntity getUserExtendInfo() {
		return userExtendInfo;
	}

	public void setUserExtendInfo(UserExtendInfoEntity userExtendInfo) {
		this.userExtendInfo = userExtendInfo;
	}

	public UserBaseInfoEntity getUserBaseInfo() {
		return userBaseInfo;
	}

	public void setUserBaseInfo(UserBaseInfoEntity userBaseInfo) {
		this.userBaseInfo = userBaseInfo;
	}

	public String getPayStatusName() {
		return DictionaryUtils.getredPacketPsyStatus(super.getPay_status());
	}

	public void setPayStatusName(String payStatusName) {
		this.payStatusName = payStatusName;
	}
	
	
}
