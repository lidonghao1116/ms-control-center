package com.jiacer.modules.business.validate;

import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;

/** 
* @ClassName: CoursePackageValidate 
* @Description: 课程销售管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:15:11 
*  
*/
public class CoursePackageValidate {
	/**
	 * 新增方法参数校验
	 * @param coursePackageEntity
	 * @return
	 */
	public static JsonResult addValidate(CoursePackageEntity coursePackageEntity){
		
		if(StringUtils.isEmpty(coursePackageEntity.getPackageName())){
			return new JsonResult(false,"请填写产品名称",null);
		}
		
		if(StringUtils.isEmpty(coursePackageEntity.getSummary())){
			return new JsonResult(false,"请填写产品简介",null);
		}
		
		if(StringUtils.isEmpty(coursePackageEntity.getApplyCourses())){
			return new JsonResult(false,"请填写勾选课程",null);
		}
		if(coursePackageEntity.getSortNo()==null){
			return new JsonResult(false,"请填写排序",null);
		}
		return new JsonResult(true,null,null);
	}
	
	/**
	 * 修改方法参数校验
	 * @param learnTypesEntity
	 * @return
	 */
	public static JsonResult modifyValidate(CoursePackageEntity coursePackageEntity){
		if(StringUtils.isEmpty(coursePackageEntity.getPackageName())){
			return new JsonResult(false,"请填写产品名称",null);
		}
		
		if(StringUtils.isEmpty(coursePackageEntity.getSummary())){
			return new JsonResult(false,"请填写产品简介",null);
		}
		
		if(StringUtils.isEmpty(coursePackageEntity.getStatus())){
			return new JsonResult(false,"请填写课程状态",null);
		}
		if(StringUtils.isEmpty(coursePackageEntity.getApplyCourses())){
			return new JsonResult(false,"请填写勾选课程",null);
		}
		
		if(coursePackageEntity.getSortNo()==null){
			return new JsonResult(false,"请填写排序",null);
		}
		return new JsonResult(true,null,null);
	}
}
