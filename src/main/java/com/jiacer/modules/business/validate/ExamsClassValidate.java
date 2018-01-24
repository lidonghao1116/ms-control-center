package com.jiacer.modules.business.validate;

import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;

/** 
* @ClassName: ExamsValidate 
* @Description: 考试管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:15:37 
*  
*/
public class ExamsClassValidate {

	/**
	 * 新增方法参数校验
	 * @param examClassEntity
	 * @return
	 */
	public static JsonResult addValidate(ExamClassEntity examClassEntity){
		
		if(StringUtils.isEmpty(examClassEntity.getClassName())){
			return new JsonResult(false,"请填写班级名称",null);
		}
		
		if(examClassEntity.getCourseId()==null){
			return new JsonResult(false,"请选择课程",null);
		}
		
		if(examClassEntity.getShoolId()==null){
			return new JsonResult(false,"请选择学校",null);
		}
		
		if(StringUtils.isEmpty(examClassEntity.getClassNumber())){
			return new JsonResult(false,"请填写班级标号",null);
		}
		
		return new JsonResult(true,null,null);
	}
	
	/**
	 * 修改方法参数校验
	 * @param examClassEntity
	 * @return
	 */
	public static JsonResult modifyValidate(ExamClassEntity examClassEntity){
		
		if(StringUtils.isEmpty(examClassEntity.getExamStatus())){
			return new JsonResult(false,"请选择考试状态",null);
		}
		return new JsonResult(true,null,null);
	}
	
}
