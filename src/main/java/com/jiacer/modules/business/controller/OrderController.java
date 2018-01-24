package com.jiacer.modules.business.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.business.bean.UserBean;
import com.jiacer.modules.business.bean.form.LearnRecordsQuery;
import com.jiacer.modules.business.bean.form.UsersQuery;
import com.jiacer.modules.business.service.OrderService;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.OrdersEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;

/**
 *@Description: 订单管理控制器
 *
 *@author: zhangsq
 *@date:   2017年5月31日 上午11:46:39
 * 
 */
@Controller
@RequestMapping(MappingURL.ORDER_URL)
public class OrderController extends BaseController{
	
	@Resource
	OrderService orderService;
		//列表页面
		@RequestMapping(MappingURL.LIST_URL)
		public String list(Model model) {
		    return "modules/order/list";
		}
		//用户列表查询
		@RequestMapping(MappingURL.QUERY_URL)
		@ResponseBody
		public Page<OrdersEntity> page(
				@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
				@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
				Model model,OrdersEntity ordersEntity) {
			Page<OrdersEntity> reslut=orderService.getOrdersPage(ordersEntity, pageNumber, pageSize);
		    return reslut;
		}
		
		//用户详情
		@RequestMapping(MappingURL.INFO_URL)
		public String info(Model model,String  orderNo) {
			
			List orders = new ArrayList();
			
			if(orderNo == null){
				logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "参数OrdeNo为null"));
			}else{
				orders = orderService.getOrdersInfo(orderNo);
			}
			model.addAttribute("model", orders);
		    return "modules/order/detail";
		}
		
		@RequestMapping(MappingURL.EXPORT)
		public String export(Model model,HttpServletResponse response,HttpServletRequest request,@PathVariable String type
				,OrdersEntity ordersEntity) {
			
			if(StringUtils.isEmpty(type)){
				model.addAttribute("message", "参数错误");
			}else{
				orderService.dealExport(model,ordersEntity,response,request);
			}
		    return "modules/excel/info";
		}
		
}
