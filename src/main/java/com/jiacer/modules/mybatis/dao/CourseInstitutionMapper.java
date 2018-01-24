package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.model.CourseInstitution;

@MyBatisDao
public interface CourseInstitutionMapper extends CrudDao<CourseInstitution> {

	/**
	 * 
	 * @MethodName:insertInfo
	 * @Type:CourseInstitutionMapper
	 * @Description:将课程信息插入到courseInstitution表中
	 * @Return:int
	 * @Param:@param courseInstitution
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 11, 2017 6:12:31 PM
	 */
	int insertInfo(CourseInstitution courseInstitution);
}
