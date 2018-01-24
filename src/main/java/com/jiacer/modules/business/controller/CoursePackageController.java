package com.jiacer.modules.business.controller;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiacer.modules.business.service.CoursePackageService;
import com.jiacer.modules.business.validate.CoursePackageValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;

/** 
* @ClassName: CoursePackageController 
* @Description: 课程销售管理控制类
* @author 贺章鹏
* @date 2016年10月19日 下午12:47:21 
*  
*/
@Controller
@RequestMapping(MappingURL.COURSES_PACKAGE_URL)
public class CoursePackageController extends BaseController{
	
	@Resource
	CoursePackageService coursePackageService;
	
	public CoursePackageEntity getModel(Integer id){
		if(id != null){
			return coursePackageService.getCoursePackageById(id);
		}else{
			return new CoursePackageEntity();
		}
	}
	
	//新增修改页面
	@RequestMapping(MappingURL.FORM_URL)
	public String form(Model model,Integer id) {
		
		CoursePackageEntity coursePackageEntity=this.getModel(id);
		//List<CourseInfoEntity> list=CoursesUtils.init();
		if(coursePackageEntity==null || coursePackageEntity.getId() == null){
			model.addAttribute("update", Boolean.FALSE);
			model.addAttribute("model", null);
		}else{
			model.addAttribute("update", Boolean.TRUE);
			model.addAttribute("model", coursePackageEntity);
		}
		//model.addAttribute("courses", list);
	    return "modules/coursePackage/form";
	}
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/coursePackage/list";
	}
	
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<CoursePackageEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,CoursePackageEntity coursePackageEntity) {
		Page<CoursePackageEntity> reslut=coursePackageService.getCoursePackagePage(coursePackageEntity, pageNumber, pageSize);
	    return reslut;
	}
	
	
	//新增
	@RequestMapping(MappingURL.ADD_URL)
	@ResponseBody
	public JsonResult add(Model model,CoursePackageEntity coursePackageEntity) {
		if(coursePackageEntity == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "课程销售对象为null"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=CoursePackageValidate.addValidate(coursePackageEntity);
		
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		
		try {
			coursePackageService.addCoursePackage(coursePackageEntity);
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
	public JsonResult modify(Model model,CoursePackageEntity coursePackageEntity) {
		if(coursePackageEntity == null || coursePackageEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "课程销售对象对象为null或课程销售对象id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=CoursePackageValidate.modifyValidate(coursePackageEntity);
		
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		
		try {
			coursePackageService.modifyCoursePackage(coursePackageEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	//删除
	@RequestMapping(MappingURL.DELETE_URL)
	@ResponseBody
	public JsonResult delete(Model model,CoursePackageEntity coursePackageEntity) {
		if(coursePackageEntity == null || coursePackageEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "课程销售对象对象为null或课程销售对象id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult jsonResult=null;
		
		try {
			coursePackageService.delCoursePackage(coursePackageEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
}
