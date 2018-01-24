package com.jiacer.modules.business.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiacer.modules.common.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jiacer.modules.business.bean.form.SchoolsForm;
import com.jiacer.modules.business.service.SchoolsService;
import com.jiacer.modules.business.validate.SchoolsValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;

/** 
* @ClassName: schoolsController 
* @Description: 分校管理--学校管理控制器
* @author zhangsq
* @date 2017年06月26日 下午1:24:57 
*  
*/
@Controller
@RequestMapping(MappingURL.SCHOOLS_URL)
public class SchoolsController extends BaseController{

	@Resource
	SchoolsService schoolsService;
	
	public SchoolsEntity getModel(Integer id){
		if(id != null){
			return schoolsService.getSchoolsById(id);
		}else{
			return new SchoolsEntity();
		}
	}
	
	//新增修改页面
	@RequestMapping(MappingURL.FORM_URL)
	public String form(Model model,Integer id) {
		
		SchoolsEntity schoolsEntity=this.getModel(id);
		if(schoolsEntity==null || schoolsEntity.getId() == null){
			model.addAttribute("update", Boolean.FALSE);
			model.addAttribute("model", null);
		}else{
			model.addAttribute("update", Boolean.TRUE);
			model.addAttribute("model", schoolsEntity);
			System.out.println(""+schoolsEntity.getSysUsers().getLoginAccount());
		}
	    return "modules/schools/form";
	}
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/schools/list";
	}
	
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<SchoolsEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,SchoolsEntity schoolsEntity) {
		Page<SchoolsEntity> reslut=schoolsService.getSchoolsPage(schoolsEntity, pageNumber, pageSize);
	    return reslut;
	}
	
	
	//新增
	@RequestMapping(value=MappingURL.ADD_URL,method={RequestMethod.POST,RequestMethod.GET})
	public String add(HttpServletRequest request,HttpServletResponse response,SchoolsForm schoolsEntity){
		System.out.println("=====from 进入");
		try {
			
			schoolsService.addschools(schoolsEntity);
			return "modules/schools/list";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			return "modules/schools/list";
		}
	}

	//修改
	@RequestMapping(value=MappingURL.MODIFY_URL,method={RequestMethod.POST,RequestMethod.GET})
	public String modify(HttpServletRequest request,HttpServletResponse response,SchoolsForm schoolsEntity){
		try {
			schoolsService.modifyschools(schoolsEntity);
			return "modules/schools/list";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
		}
		return "modules/schools/list";
	}
	//修改
	@RequestMapping(value=MappingURL.RESET_PWD_URL,method={RequestMethod.POST})
	@ResponseBody
	public JsonResult resetPwd(SchoolsForm schoolsEntity){
		try {
			if(schoolsEntity == null || schoolsEntity.getId() == null){
				logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "重置密码的学校ID为空"));
				return new JsonResult(false,"重置密码的学校ID为空",null);
			}

			schoolsService.resetPwd(schoolsEntity);
			return new JsonResult(true, "密码重置成功，初始密码：000000", null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
		}
		return new JsonResult(false,"重置密码失败，请重试",null);
	}
	
	//check
	@RequestMapping(MappingURL.CHECK_URL)
	@ResponseBody
	public JsonResult check (String loginAccount){ 
		if (loginAccount==null) {
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "新增学校管理员账户为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		JsonResult jsonResult=null;
		try {
			
			return schoolsService.check(loginAccount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);;
		}
		return jsonResult;   
	}  
	
	
}
