package com.jiacer.modules.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.mybatis.model.Orders;
import com.jiacer.modules.system.utils.DictionaryUtils;

public class OrdersEntity extends Orders implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/*************用于查询条件**************/
	
	private String wechatNick;
	
	private String mobile;
	
	private Date startDate;
	
	private Date endDate;

	/*************用于显示中文名称**************/
	@SuppressWarnings("unused")
	private String courseName;
	@SuppressWarnings("unused")
	private String tradeChannelName;
	@SuppressWarnings("unused")
	private String isInvitedName;
	
	public String getCourseName() {
		return CoursesUtils.getName(super.getCourseId());
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public String getWechatNick() {
		return wechatNick;
	}

	public void setWechatNick(String wechatNick) {
		this.wechatNick = wechatNick;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTradeChannelName() {
		return DictionaryUtils.getChannelType(super.getTradeChannel());
	}

	public void setTradeChannelName(String tradeChannelName) {
		this.tradeChannelName = tradeChannelName;
	}

	public String getIsInvitedName() {
		return DictionaryUtils.getReSign(super.getIsInvited());
	}

	public void setIsInvitedName(String isInvitedName) {
		this.isInvitedName = isInvitedName;
	}

	
}
