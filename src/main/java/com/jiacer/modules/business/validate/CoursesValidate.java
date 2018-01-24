package com.jiacer.modules.business.validate;

import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.CourseInfoEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.TemplateInfoEntity;
import com.jiacer.modules.mybatis.model.CourseInfo;

/** 
* @ClassName: CoursesValidate 
* @Description: 课程管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:15:23 
*  
*/
public class CoursesValidate {
	
	/**
	 * 新增方法参数校验
	 * @param learnTypesEntity
	 * @return
	 */
	public static JsonResult addValidate(CourseInfoEntity courseInfo){
		TemplateInfoEntity parm = courseInfo.getTemplateInfo();
		if(StringUtils.isEmpty(courseInfo.getCourseName())){
			return new JsonResult(false,"请填写课程名称",null);
		}
		if (StringUtils.isEmpty(courseInfo.getFitService())) {
			return new JsonResult(false,"请填写适合工种",null);
		}
		if(courseInfo.getCourseType()==null){
			return new JsonResult(false,"请选择课程形式",null);
		}
		if(courseInfo.getPrice()==null){
			return new JsonResult(false,"请写填课程价格",null);
		}
		if (courseInfo.getSortNo() == null) {
			return new JsonResult(false,"请写填课程排序",null);
		}
		if (courseInfo.getStatus() == null) {
			return new JsonResult(false,"请写选择是否上架",null);
		}
		
		if (courseInfo.getCourseType()!=null&&(courseInfo.getCourseType().equals("02")||courseInfo.getCourseType().equals("03"))) {
			//答题  视频加答题
			
			if (StringUtils.isEmpty(courseInfo.getCourseTime())) {
				return new JsonResult(false,"请填写答题时间",null);
			}
			
			
			if (StringUtils.isEmpty(parm.getTf())&&StringUtils.isEmpty(parm.getSc())&&StringUtils.isEmpty(parm.getMc())) {
				return new JsonResult(false,"请至少选择一种题型",null);
			}
			//判断题 分数不为空 题目数也不能为空
			if (StringUtils.isNotEmpty(parm.getTf())) {
				if (StringUtils.isEmpty(parm.getSubjectCountTF())) {
					return new JsonResult(false,"请填写判断题题目数",null);
				}
			}
			//单选题 分数不为空  题目数也不能为空
			if (StringUtils.isNotEmpty(parm.getSc())) {
				if (StringUtils.isEmpty(parm.getSubjectCountSC())) {
					return new JsonResult(false,"请填写单选题题目数",null);
				}
			}
			//多选题 分数不为空  题目数也不能为空
			if (StringUtils.isNotEmpty(parm.getMc())) {
				if (StringUtils.isEmpty(parm.getSubjectCountMC())) {
					return new JsonResult(false,"请填写多选题题目数",null);
				}
			}
		}
		return new JsonResult(true,null,null);
	}
	
	/**
	 * 修改方法参数校验
	 * @return
	 */
	public static JsonResult modifyValidate(CourseInfo courseInfo){
		if(StringUtils.isEmpty(courseInfo.getCourseName())){
			return new JsonResult(false,"请填写课程名称",null);
		}
		if (StringUtils.isEmpty(courseInfo.getFitService())) {
			return new JsonResult(false,"请填写适合工种",null);
		}
		if(courseInfo.getCourseType()==null){
			return new JsonResult(false,"请选择课程形式",null);
		}
		if(courseInfo.getPrice()==null){
			return new JsonResult(false,"请写填课程价格",null);
		}
		if (courseInfo.getSortNo() == null) {
			return new JsonResult(false,"请写填课程排序",null);
		}
		if (courseInfo.getStatus() == null) {
			return new JsonResult(false,"请写选择是否上架",null);
		}
		if( !courseInfo.getCourseType().equals("01") && StringUtils.isEmpty(courseInfo.getCourseTime())) {
			return new JsonResult(false,"请填写答题时间",null);
		}
		
		return new JsonResult(true,null,null);
	}

}
