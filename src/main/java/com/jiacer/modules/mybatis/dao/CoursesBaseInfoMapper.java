package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.business.bean.form.CourseBaseInfoForm;
import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.CourseBaseInfoEntity;
import com.jiacer.modules.mybatis.model.CourseBaseInfo;

@MyBatisDao
public interface CoursesBaseInfoMapper extends CrudDao<CourseBaseInfo>  {

	/**
	 * 
	 * @MethodName:getCourseInfo
	 * @Type:CoursesBaseInfoMapper
	 * @Description:获取总控基础课程信息
	 * @Return:List<CourseBaseInfo>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 26, 2017 11:03:56 AM
	 */
	List<CourseBaseInfoEntity> getCourseInfo();

	/**
	 * 
	 * @param map 
	 * @MethodName:getCourseBaseInfoCount
	 * @Type:CoursesBaseInfoMapper
	 * @Description:获取总控基础课程信息总条数
	 * @Return:Integer
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 26, 2017 2:57:13 PM
	 */
	Integer getCourseBaseInfoCount(Map<Object, Object> map);

	/**
	 * 
	 * @MethodName:getCoursesBaseInfoPageList
	 * @Type:CoursesBaseInfoMapper
	 * @Description:分页查询总控基础课程信息
	 * @Return:List<CourseBaseInfoEntity>
	 * @Param:@param map
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 26, 2017 3:16:32 PM
	 */
	List<CourseBaseInfoEntity> getCoursesBaseInfoPageList(Map<Object, Object> map);

	/**
	 * 
	 * @MethodName:getCourseBaseInfoByParams
	 * @Type:CoursesBaseInfoMapper
	 * @Description:通过新增基础课程参数获取数据库中信息
	 * @Return:CourseBaseInfoEntity
	 * @Param:@param courseBaseInfoForm
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 26, 2017 11:07:41 PM
	 */
	CourseBaseInfoEntity getCourseBaseInfoByParams(CourseBaseInfoForm courseBaseInfoForm);

	/**
	 * 
	 * @MethodName:addCourseBaseInfoByEntityForm
	 * @Type:CoursesBaseInfoMapper
	 * @Description:总控--分校管理--课程管理--新增
	 * @Return:int
	 * @Param:@param cInfo
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 27, 2017 10:58:11 AM
	 */
	int addCourseBaseInfoByEntityForm(CourseBaseInfo cInfo);

	/**
	 * 
	 * @MethodName:updaeCourseBaseInfo
	 * @Type:CoursesBaseInfoMapper
	 * @Description:总控--分校管理--课程管理--修改
	 * @Return:int
	 * @Param:@param cEntity
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 27, 2017 2:47:19 PM
	 */
	int updaeCourseBaseInfo(CourseBaseInfoEntity cEntity);

	/**
	 * 
	 * @MethodName:getCourseBaseInfoById
	 * @Type:CoursesBaseInfoMapper
	 * @Description:获取单个课程信息
	 * @Return:CourseBaseInfoEntity
	 * @Param:@param courseId
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 29, 2017 10:32:19 AM
	 */
	CourseBaseInfoEntity getCourseBaseInfoById(Integer courseId);

	
}
