package com.jiacer.modules.business.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiacer.modules.business.bean.UserBean;
import com.jiacer.modules.business.bean.form.UsersQuery;
import com.jiacer.modules.business.service.UsersService;
import com.jiacer.modules.business.validate.UsersValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;

/** 
* @ClassName: UsersController 
* @Description: 学员管理控制类
* @author 贺章鹏
* @date 2016年10月19日 下午12:48:07 
*  
*/
@Controller
@RequestMapping(MappingURL.USER_BASE_URL)
public class UsersController extends BaseController{
	
	@Resource
	UsersService usersService;
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/csUsers/list";
	}
	
	//用户列表查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<UserBaseInfoEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,UsersQuery usersQuery) {
		Page<UserBaseInfoEntity> reslut=usersService.getUsersPage(usersQuery, pageNumber, pageSize);
	    return reslut;
	}
	
	//用户详情
	@RequestMapping(MappingURL.INFO_URL)
	public String info(Model model,Integer id) {
		
		UserBean user=new UserBean();
		if(id == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "参数id为null"));
		}else{
			user=usersService.getUsersInfo(id);
		}
		model.addAttribute("model", user);
	    return "modules/csUsers/detail";
	}
	
/*	//新增修改页面
	@RequestMapping(MappingURL.FORM_URL)
	public String form(Model model,Integer id) {
		if(id == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "参数id为null"));
			return "erros/illegalRequest";
		}else{
			UserBean user=usersService.getUsers(id);
			model.addAttribute("model", user);
		    return "modules/csUsers/form";
		}
	}*/
	
}
