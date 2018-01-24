package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.model.LearnTypes;

@MyBatisDao
public interface LearnTypesMapper extends CrudDao<LearnTypesEntity>{

	/**
	 * @param map
	 * @return
	 */
	Integer count(Map<Object, Object> map);
	
	Integer schoolCoursecount(Map<Object, Object> map);

	/**
	 * @param map
	 * @return
	 */
	List<LearnTypesEntity> getPageList(Map<Object, Object> map);
	
	/***
	 * 
	 * @MethodName:getLeanTypeByCourseBaseId
	 * @Type:LearnTypesMapper
	 * @Description:获取LeanTypeInfo信息通过CourseBaseId
	 * @Return:LearnTypes
	 * @Param:@param courseId
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 4, 2017 10:08:42 AM
	 */
	List<LearnTypes> getLeanTypeByCourseBaseId(Integer courseId);
	
	/**
	 * 
	 * @MethodName:updateCourseStatus
	 * @Type:LearnTypesMapper
	 * @Description:更改对应课程的课程状态针对于下架
	 * @Return:void
	 * @Param:@param map
	 * @Thrown:
	 * @Date:Oct 12, 2017 3:54:51 PM
	 */
	void updateCourseStatus(Map<Object, Object> map);
}