package com.jiacer.modules.business.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiacer.modules.business.service.CoursesService;
import com.jiacer.modules.business.validate.CoursesValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.CourseInfoEntity;
import com.jiacer.modules.mybatis.model.CourseInfo;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.DictionaryUtils;


/**
 *@Description:  在线学堂---课程管理控制类
 *
 *@author: zhangsq
 *@date:   2017年5月20日 下午4:35:10
 * 
 */
@Controller
@RequestMapping(MappingURL.COURSE_BASE_URL)
public class CourseController extends BaseController{
	
	@Resource
	CoursesService coursesService;
	
	public CourseInfoEntity getModel(Integer courseId){
		if(courseId != null){
			return coursesService.getCoursesById(courseId);
		}else{
			return new CourseInfoEntity();
		}
	}
	
	//新增修改页面
	@RequestMapping(MappingURL.FORM_URL)
	public String form(Model model,Integer courseId) {
		
		CourseInfoEntity courseInfoEntity=this.getModel(courseId);
		
		if(courseInfoEntity==null || courseInfoEntity.getCourseId() == null){
			model.addAttribute("update", Boolean.FALSE);
			model.addAttribute("model", null);
		}else{
			model.addAttribute("update", Boolean.TRUE);
			model.addAttribute("model", courseInfoEntity);
		}
	    return "modules/course/form";
	}
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/course/list";
	}
	
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<CourseInfoEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,CourseInfoEntity courseInfoEntity) {
		Page<CourseInfoEntity> reslut=coursesService.getCoursesPage(courseInfoEntity, pageNumber, pageSize);
		//DictionaryUtils.getCoursStatus("01");
		return reslut;
	}
	
	
	//新增
	@RequestMapping(MappingURL.ADD_URL)
	@ResponseBody
	public JsonResult add(Model model,@RequestBody CourseInfoEntity courseInfoEntity) {
		if(courseInfoEntity == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "课程管理对象为null"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=CoursesValidate.addValidate(courseInfoEntity);
		
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		
		try {
			coursesService.addCourses(courseInfoEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	//修改
	@RequestMapping(MappingURL.MODIFY_URL)
	@ResponseBody
	public JsonResult modify(Model model,@RequestBody CourseInfoEntity courseInfoEntity) {
		if(courseInfoEntity == null || courseInfoEntity.getCourseId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "课程管理对象为null或课程管理id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=CoursesValidate.modifyValidate(courseInfoEntity);
		
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		
		try {
			coursesService.modifyCourses(courseInfoEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	
	
}
