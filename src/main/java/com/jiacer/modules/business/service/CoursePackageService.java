package com.jiacer.modules.business.service;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;

/** 
* @ClassName: CoursePackageService 
* @Description: 课程销售管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:05:56 
*  
*/
public interface CoursePackageService {

	/**
	 * 根据id获取课程销售对象
	 * @param id
	 * @return
	 */
	CoursePackageEntity getCoursePackageById(Integer id);

	/**
	 * 课程销售分页
	 * @param coursePackageEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<CoursePackageEntity> getCoursePackagePage(CoursePackageEntity coursePackageEntity, int pageNumber,
			int pageSize);

	/**
	 * 新增课程销售
	 * @param coursePackageEntity
	 * @throws Exception
	 */
	void addCoursePackage(CoursePackageEntity coursePackageEntity) throws Exception;

	/**
	 * 修改课程销售
	 * @param coursePackageEntity
	 * @throws Exception
	 */
	void modifyCoursePackage(CoursePackageEntity coursePackageEntity) throws Exception;

	/**
	 * 删除课程销售
	 * @param coursePackageEntity
	 * @throws Exception
	 */
	void delCoursePackage(CoursePackageEntity coursePackageEntity) throws Exception;

}
