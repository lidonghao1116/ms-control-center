package com.jiacer.modules.business.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.business.service.AuthorityService;
import com.jiacer.modules.business.validate.AuthorityValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.AuthorityEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
/**
 *@Description: 发证机构管理控制器
 *
 *@author: zhangsq
 *@date:   2017年5月27日 上午11:48:29
 * 
 */
@Controller
@RequestMapping(MappingURL.CERT_AUTHORITY_URL)
public class AuthorityController extends BaseController{

	@Resource
	AuthorityService authorityService;
	
		
		public AuthorityEntity getModel(Integer authorityId){
			if(authorityId != null){
				return authorityService.getById(authorityId);
			}else{
				return new AuthorityEntity();
			}
		}
	
	
		//新增页面
		@RequestMapping(MappingURL.FORM_URL)
		public String form(Model model,Integer authorityId) {
			
			AuthorityEntity authorityEntity=this.getModel(authorityId);
			
			if(authorityEntity==null || authorityEntity.getAuthorityId() == null){
				model.addAttribute("update", Boolean.FALSE);
				model.addAttribute("model", null);
			}else{
				model.addAttribute("update", Boolean.TRUE);
				model.addAttribute("model", authorityEntity);
			}
		    return "modules/authority/form";
		}
	
	
	 //列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/authority/list";
	}
	
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<AuthorityEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,AuthorityEntity authorityEntity) {
		Page<AuthorityEntity> reslut=authorityService.getAuthorityPage(authorityEntity, pageNumber, pageSize);
		return reslut;
	}
	//新增
	@RequestMapping(MappingURL.ADD_URL)
	@ResponseBody
	public JsonResult add(Model model,AuthorityEntity authorityEntity) {
		if(authorityEntity == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "发证机构对象为null"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=AuthorityValidate.addValidate(authorityEntity);
		
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		
		try {
			authorityService.addAuthority(authorityEntity);
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
		public JsonResult modify(Model model,AuthorityEntity authorityEntity) {
			JsonResult jsonResult=null;
			try {
				authorityService.modifyAuthority(authorityEntity);
				jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
				jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
			}
			return jsonResult;
		}
		
}
