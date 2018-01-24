package com.jiacer.modules.business.service;

import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;

/** 
* @ClassName: ApplyOrdersService 
* @Description: 学员申请订单管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:05:11 
*  
*/
public interface ApplyOrdersService {

	/**
	 * 报名管理分页
	 * @param applyOrdersEntity
	 * @param type 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<ApplyOrdersEntity> getApplyOrdersPage(ApplyOrdersEntity applyOrdersEntity, int pageNumber, int pageSize);

	

	/**
	 * @param id
	 * @return
	 */
	ApplyOrdersEntity getApplyOrders(Integer id);

	
	
	/**
	 * 处理已报名学员excel导出功能
	 * @param model
	 * @param entity
	 * @param response
	 * @return 
	 */
	Model dealExport(Model model, ApplyOrdersEntity entity,HttpServletResponse response);

	

}
