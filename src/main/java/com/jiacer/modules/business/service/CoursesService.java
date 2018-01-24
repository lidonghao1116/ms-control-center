package com.jiacer.modules.business.service;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.CourseInfoEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.model.CourseInfo;

/** 
* @ClassName: CoursesService 
* @Description: 在线学堂-课程管理接口服务
* @author zsq
* @date 
*  
*/
public interface CoursesService {

	/**
	 * 根据id获取课程分类管理对象
	 * @param id
	 * @return
	 */
	CourseInfoEntity getCoursesById(Integer courseId);

	/**
	 * 课程管理分页
	 * @param learnTypesEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<CourseInfoEntity> getCoursesPage(CourseInfoEntity courseInfoEntity, int pageNumber, int pageSize);

	/**
	 * 新增课程
	 * @param learnTypesEntity
	 * @throws Exception
	 */
	void addCourses(CourseInfoEntity courseInfoEntity) throws Exception;

	/**
	 * 修改课程
	 * @param learnTypesEntity
	 * @throws Exception
	 */
	void modifyCourses(CourseInfoEntity courseInfoEntity) throws Exception;

	

}
