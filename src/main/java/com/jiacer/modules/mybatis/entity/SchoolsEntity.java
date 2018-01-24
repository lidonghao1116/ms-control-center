package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.mybatis.model.Schools;
import com.jiacer.modules.system.utils.AreasUtils;

/**
 * 
* @ClassName: ShoolsEntity 
* @Description: 学校表
* @author 贺章鹏
* @date 2016年10月18日 下午12:08:20 
*
 */
public class SchoolsEntity extends Schools{

	private static final long serialVersionUID = 1L;
	
	private SysUsersEntity SysUsers;
	
	

	/***********用于中文显示***************/
	@SuppressWarnings("unused")
	private String privinceName;
	
	@SuppressWarnings("unused")
	private String cityName;

	@SuppressWarnings("unused")
	private String areaName;
	
	public String getPrivinceName() {
		if (super.getPrivince()!=null&&!super.getPrivince().equals("")) {
			return AreasUtils.getName(super.getPrivince());
		}else {
			return privinceName;
		}
	}

	public void setPrivinceName(String privinceName) {
		this.privinceName = privinceName;
	}

	public String getCityName() {
		if (super.getCity()!=null&&!super.getCity().equals("")) {
			return AreasUtils.getName(super.getCity());
		}else {
			return cityName;
		}
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		if (super.getArea()!=null&&!super.getArea().equals("")) {
			return AreasUtils.getName(super.getArea());
		}else {
			return areaName;
		}
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	
	public SysUsersEntity getSysUsers() {
		return SysUsers;
	}

	public void setSysUsers(SysUsersEntity sysUsers) {
		SysUsers = sysUsers;
	}

}
