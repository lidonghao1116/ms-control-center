package com.jiacer.modules.business.validate;

import com.jiacer.modules.business.bean.form.CourseBaseInfoForm;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.CoursesBaseInfoMapper;
import com.jiacer.modules.mybatis.entity.CourseBaseInfoEntity;

/**
 * 
 * @ClassName:CourseBaseInfoValidate.java
 * @Description:基础课程信息检验类
 * @Author:ticahmfock
 * @Date:Sep 26, 2017 10:50:42 PM
 */
public class CourseBaseInfoValidate {
	
	private static CoursesBaseInfoMapper coursesBaseInfoMapper = SpringContextHolder.getBean(CoursesBaseInfoMapper.class);
	/**
	 * 
	 * @MethodName:addCourseBaseInfoValidate
	 * @Type:CourseBaseInfoValidate
	 * @Description:基础课程--新增课程参数验证
	 * @Return:JsonResult
	 * @Param:@param courseBaseInfoForm
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 26, 2017 10:53:00 PM
	 */
	public static JsonResult addCourseBaseInfoValidate(CourseBaseInfoForm courseBaseInfoForm){
		
		if (StringUtils.isEmpty(courseBaseInfoForm.getCourseName())) {
			return new JsonResult(false,"请填写课程名称",null);
		}
		if (StringUtils.isEmpty(courseBaseInfoForm.getCertName())) {
			return new JsonResult(false,"请填写证书名称",null);
		}
		if (StringUtils.isEmpty(courseBaseInfoForm.getAuthenticateGrade())) {
			return new JsonResult(false,"请选择鉴定等级",null);
		}
		if (courseBaseInfoForm.getTotalHours()==null||courseBaseInfoForm.getTotalHours()<=0) {
			return new JsonResult(false,"请输入总课时",null);
		}
		if (StringUtils.isEmpty(courseBaseInfoForm.getExamType())) {
			return new JsonResult(false,"请选择考试形式",null);
		}
		if (courseBaseInfoForm.getAuthorityId()==null||courseBaseInfoForm.getAuthorityId()<=0) {
			return new JsonResult(false,"请选择发证机构",null);
		}
		CourseBaseInfoEntity condition = coursesBaseInfoMapper.getCourseBaseInfoByParams(courseBaseInfoForm);
		if (condition !=null) {
			return new JsonResult(false,"该课程信息已存在，请重新输入",null);
		}
		return new JsonResult(true,null,null);
		
	}
	/**
	 * 
	 * @MethodName:updateCourseBaseInfoValidate
	 * @Type:CourseBaseInfoValidate
	 * @Description:修改课程参数验证
	 * @Return:JsonResult
	 * @Param:@param courseBaseInfoForm
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 5, 2017 12:54:10 AM
	 */
	public static JsonResult updateCourseBaseInfoValidate(CourseBaseInfoForm courseBaseInfoForm) {
		if (courseBaseInfoForm.getCourseId()==null||courseBaseInfoForm.getCourseId()<=0) {
			return new JsonResult(false,"操作失败,未获取该用户ID",null);
		}
		if (courseBaseInfoForm.getTotalHours()==null||courseBaseInfoForm.getTotalHours()<=0) {
			return new JsonResult(false,"请填写总课时",null);
		}
		if (StringUtils.isEmpty(courseBaseInfoForm.getExamType())) {
			return new JsonResult(false,"请选择考试形式",null);
		}
		if (StringUtils.isEmpty(courseBaseInfoForm.getStatus())) {
			return new JsonResult(false,"请选择课程状态",null);
		}
		return new JsonResult(true,null,null);
	}
}
