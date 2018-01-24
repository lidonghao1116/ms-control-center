package com.jiacer.modules.business.service;

import com.jiacer.modules.business.bean.form.CourseBaseInfoForm;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.CourseBaseInfoEntity;

/**
 * @ClassName: SchoolCourseService 
 * @Description: 分校管理--课程管理
 * @author Mr Zhangsq
 *
 */
public interface SchoolCourseService {

	/**
	 * 
	 * @MethodName:getSchoolCoursePage
	 * @Type:SchoolCourseService
	 * @Description:总控--分校管理--课程管理--首页列表
	 * @Return:Page<CourseBaseInfoEntity>
	 * @Param:@param courseBaseInfoEntity
	 * @Param:@param pageNumber
	 * @Param:@param pageSize
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 26, 2017 2:12:15 PM
	 */
	Page<CourseBaseInfoEntity> getSchoolCoursePage(CourseBaseInfoEntity courseBaseInfoEntity, int pageNumber, int pageSize);

	/**
	 * 
	 * @MethodName:addCourseBaseInfo
	 * @Type:SchoolCourseService
	 * @Description:总控--分校管理--课程管理--新增
	 * @Return:int
	 * @Param:@param courseBaseInfoForm
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 27, 2017 10:21:44 AM
	 */
	int addCourseBaseInfo(CourseBaseInfoForm courseBaseInfoForm);
	
	/**
	 * 
	 * @MethodName:updateCourseBaseInfo
	 * @Type:SchoolCourseService
	 * @Description:
	 * @Return:void
	 * @Param:@param courseBaseInfoForm
	 * @Thrown:
	 * @Date:Sep 27, 2017 2:04:06 PM
	 */
	int updateCourseBaseInfo(CourseBaseInfoForm courseBaseInfoForm);
	
	/**
	 * 
	 * @MethodName:getCourseBaseInfoById
	 * @Type:SchoolCourseService
	 * @Description:获取单个课程信息
	 * @Return:CourseBaseInfoEntity
	 * @Param:@param courseId
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 29, 2017 10:30:24 AM
	 */
	CourseBaseInfoEntity getCourseBaseInfoById(Integer courseId);
	
}
