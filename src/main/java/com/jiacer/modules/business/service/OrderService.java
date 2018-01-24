package com.jiacer.modules.business.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jiacer.modules.business.bean.form.LearnRecordsQuery;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.OrdersEntity;

public interface OrderService {

	/**
	 * 订单记录分页
	 * @param ordersEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<OrdersEntity> getOrdersPage(OrdersEntity ordersEntity, int pageNumber, int pageSize);
	
	/**
	 * 获取订单详情根据OrderNo
	 * @param OrderNo
	 * @return
	 */
	List getOrdersInfo(String  orderNo);
	
	
	/**
	 * @param model
	 * @param ordersEntity
	 * @param response
	 * @param request
	 */
	Model dealExport(Model model, OrdersEntity ordersEntity, HttpServletResponse response,
			HttpServletRequest request);
}
