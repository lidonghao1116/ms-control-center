package com.jiacer.modules.business.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiacer.modules.business.bean.HandleStatusType;
import com.jiacer.modules.business.bean.OptType;
import com.jiacer.modules.business.service.ApplyOrdersService;
import com.jiacer.modules.business.validate.ApplyOrdersValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;

/** 
* @ClassName: ApplyOrdersController 
* @Description: 学员申请订单管理 
* @author zhangsq
* @date 
*  
*/

@Controller
@RequestMapping(MappingURL.APPLY_ORDER_URL)
public class ApplyOrdersController extends BaseController{
	
	@Autowired
	ApplyOrdersService applyOrdersService;
	
	public ApplyOrdersEntity getModel(Integer id){
		if(id != null){
			return applyOrdersService.getApplyOrders(id);
		}else{
			return new ApplyOrdersEntity();
		}
	}
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/applyOrder/list";
	}
	
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<ApplyOrdersEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,ApplyOrdersEntity applyOrdersEntity) {
		Page<ApplyOrdersEntity> reslut=applyOrdersService.getApplyOrdersPage(applyOrdersEntity, pageNumber, pageSize);
	    return reslut;
	}
	
	
	//报考信息详细
	@RequestMapping(MappingURL.INFO_URL)
	public String info(Model model,Integer id) {
		ApplyOrdersEntity entity=applyOrdersService.getApplyOrders(id);
		model.addAttribute("model", entity);
	    return "modules/applyOrder/detail";
	}
	
	@RequestMapping(MappingURL.EXPORT)
	public String export(Model model,HttpServletResponse response,HttpServletRequest request, @PathVariable String type
			,ApplyOrdersEntity entity) {
		if(StringUtils.isEmpty(type)){
			model.addAttribute("message", "参数错误");
		}else{
			applyOrdersService.dealExport(model,entity,response);
		}
	    return "modules/excel/info";
	}
	
}
