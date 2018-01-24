package com.jiacer.modules.business.controller;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.business.bean.form.CourseBaseInfoForm;
import com.jiacer.modules.business.service.SchoolCourseService;
import com.jiacer.modules.business.validate.CourseBaseInfoValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.CourseBaseInfoEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;


/** 
* @ClassName: schoolCourseController 
* @Description: 分校管理--课程管理控制器
* @author zhangsq
* @date 
*  
*/

@Controller
@RequestMapping(MappingURL.SCHOOL_COURSE_URL)
public class SchoolCourseController extends BaseController{
	
	private final static Logger log=  LoggerFactory.getLogger(SchoolCourseController.class);
	
	@Autowired
	SchoolCourseService schoolCourseService;
	
	/**
	 * 
	 * @MethodName:list
	 * @Type:SchoolCourseController
	 * @Description:总控--分校管理--课程管理--首页页面
	 * @Return:String
	 * @Param:@param model
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 11, 2017 4:54:36 PM
	 */
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/schoolCourse/list";
	}
	
	/**
	 * 
	 * @throws UnsupportedEncodingException 
	 * @MethodName:page
	 * @Type:SchoolCourseController
	 * @Description:总控--分校管理--课程管理--查询
	 * @Return:Page<CourseBaseInfoEntity>
	 * @Param:@param pageNumber
	 * @Param:@param pageSize
	 * @Param:@param model
	 * @Param:@param courseBaseInfoEntity
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 29, 2017 5:31:23 PM
	 */
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<CourseBaseInfoEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,CourseBaseInfoEntity courseBaseInfoEntity,HttpServletRequest request) throws UnsupportedEncodingException {
		
		Page<CourseBaseInfoEntity> reslut=schoolCourseService.getSchoolCoursePage(courseBaseInfoEntity, pageNumber, pageSize);
		return reslut;
	}
	
	/**
	 * 
	 * @MethodName:addCourseBaseInfo
	 * @Type:SchoolCourseController
	 * @Description:总控--分校管理--课程管理--新增课程--页面
	 * @Return:String
	 * @Param:@param request
	 * @Param:@param model
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 26, 2017 10:24:11 PM
	 */
	@RequestMapping(value=MappingURL.ADD_COURSE_BASE_INFO,method={RequestMethod.GET,RequestMethod.POST})
	public String addCourseBaseInfo(HttpServletRequest request,Model model){
		
		return "modules/schoolCourse/addCourse";
	}
	
	/**
	 * 
	 * @MethodName:saveCourseBaseInfo
	 * @Type:SchoolCourseController
	 * @Description:总控--分校管理--课程管理--新增课程
	 * @Return:JsonResult
	 * @Param:@param request
	 * @Param:@param courseBaseInfoForm
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 26, 2017 10:42:42 PM
	 */
	@RequestMapping(value=MappingURL.SAVE_COURSE_BASE_INFO,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult saveCourseBaseInfo(HttpServletRequest request,CourseBaseInfoForm courseBaseInfoForm){
	
		JsonResult validate = CourseBaseInfoValidate.addCourseBaseInfoValidate(courseBaseInfoForm);
		if (!validate.isSuccess()) {
			return validate;
		}
		JsonResult jsonResult = null;
		try {
			int result = schoolCourseService.addCourseBaseInfo(courseBaseInfoForm);
			if (result <=0) {
				return new JsonResult(false,Message.FAILED_MSG,null);
			}
			jsonResult=new JsonResult(true,Message.ADD_SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION, e));
			jsonResult = new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	/**
	 * 
	 * 
	 * @MethodName:updateCourseDetails
	 * @Type:SchoolCourseController
	 * @Description:总控--分校管理--课程管理--修改课程页面
	 * @Return:String
	 * @Param:@param model
	 * @Param:@param courseId
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 29, 2017 5:51:28 PM
	 */
	@RequestMapping(value=MappingURL.GET_COURSE_BASE_INFO,method={RequestMethod.GET,RequestMethod.POST})
	public String updateCourseDetails(Model model,Integer courseId){
		
			CourseBaseInfoEntity cEntity = schoolCourseService.getCourseBaseInfoById(courseId);
			model.addAttribute("course", cEntity);
			return "modules/schoolCourse/updateCourse";
		
	}
	
	/**
	 * 
	 * @MethodName:updateCourseBaseInfo
	 * @Type:SchoolCourseController
	 * @Description:总控--分校管理--课程管理--修改课程
	 * @Return:JsonResult
	 * @Param:@param request
	 * @Param:@param courseBaseInfoForm
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 27, 2017 5:07:55 PM
	 */
	@RequestMapping(value=MappingURL.UPDATE_COURSE_BASE_INFO,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult updateCourseBaseInfo(HttpServletRequest request,CourseBaseInfoForm courseBaseInfoForm){

		JsonResult validate = CourseBaseInfoValidate.updateCourseBaseInfoValidate(courseBaseInfoForm);
		if (!validate.isSuccess()) {
			return validate;
		}
		JsonResult jsonResult =null;
		try {
			int result =schoolCourseService.updateCourseBaseInfo(courseBaseInfoForm);
			if (result <0) {
				return jsonResult = new JsonResult(false,Message.ERROR_EXCEPTION,null);
			}
			jsonResult =new JsonResult(true,Message.UPDATE_SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION, e));
			jsonResult =new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
}
