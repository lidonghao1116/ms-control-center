package com.jiacer.modules.business.bean.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName:CourseBaseInfoVo.java
 * @Description:总控课程参数查询类
 * @Author:ticahmfock
 * @Date:Sep 26, 2017 9:30:11 AM
 */
public class CourseBaseInfoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 课程ID
	 */
	private Integer courseId;
	
	/**
	 * 课程名称
	 */
	private String  courseName;

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "CourseBaseInfoVo [courseId=" + courseId + ", courseName=" + courseName + "]";
	}
	
	

}
