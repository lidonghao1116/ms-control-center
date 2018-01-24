package com.jiacer.modules.business.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.business.service.BrokerageService;
import com.jiacer.modules.business.service.PartnersService;
import com.jiacer.modules.business.validate.PartnersValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.BrokerageSchemeEntity;
import com.jiacer.modules.mybatis.entity.PartnersEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;

/** 
* @ClassName: PartnersController 
* @Description: 合作商管理
* @author 贺章鹏
* @date 2016年10月19日 下午12:53:48 
*  
*/
@Controller
@RequestMapping(MappingURL.PARTNERS_URL)
public class PartnersController extends BaseController{
	@Resource
	PartnersService partnersService;
	
	@Resource
	BrokerageService brokerageService;
	
	/**
	 * 获取对象
	 * @param id
	 * @return
	 */
	public PartnersEntity getModel(Integer id){
		if(id != null){
			return partnersService.getPartnersById(id);
		}else{
			return new PartnersEntity();
		}
	}
	
	//新增修改页面
	@RequestMapping(MappingURL.FORM_URL)
	public String form(Model model,Integer id) {
		
		PartnersEntity partnersEntity=this.getModel(id);
		if(partnersEntity==null || partnersEntity.getId() == null){
			model.addAttribute("update", Boolean.FALSE);
			model.addAttribute("model", null);
		}else{
			model.addAttribute("update", Boolean.TRUE);
			model.addAttribute("model", partnersEntity);
		}
		
		BrokerageSchemeEntity brokerageSchemeEntity=new BrokerageSchemeEntity();
		brokerageSchemeEntity.setIsUsable(DBStatus.IsUsable.TRUE);
		List<BrokerageSchemeEntity> schemes=brokerageService.findSchemes(brokerageSchemeEntity);
		model.addAttribute("schemes", schemes);
	    return "modules/partners/form";
	}
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/partners/list";
	}
	
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<PartnersEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,PartnersEntity partnersEntity) {
		Page<PartnersEntity> reslut=partnersService.getPartnersPage(partnersEntity, pageNumber, pageSize);
	    return reslut;
	}
	
	
	//新增
	@RequestMapping(MappingURL.ADD_URL)
	@ResponseBody
	public JsonResult add(Model model,PartnersEntity partnersEntity) {
		if(partnersEntity == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "合作商对象为null"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=PartnersValidate.addValidate(partnersEntity);
		
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		
		try {
			partnersService.addPartners(partnersEntity);
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
	public JsonResult modify(Model model,PartnersEntity partnersEntity) {
		if(partnersEntity == null || partnersEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "合作商对象为null或合作商对象id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=PartnersValidate.modifyValidate(partnersEntity);
		
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		
		try {
			partnersService.modifyPartners(partnersEntity);
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
	public JsonResult delete(Model model,PartnersEntity partnersEntity) {
		if(partnersEntity == null || partnersEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "合作商对象为null或合作商对象id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult jsonResult=null;
		
		try {
			partnersService.delPartner(partnersEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
}
