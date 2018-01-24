package com.jiacer.modules.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.business.service.RedPacketService;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.AuthorityEntity;
import com.jiacer.modules.mybatis.entity.RedPacketEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;

/**
 *@Description: 红包清算控制器
 *
 *@author: zhangsq
 *@date:   2017年6月2日 下午4:15:40
 * 
 */
@Controller
@RequestMapping(MappingURL.RED_PACKET_URL)
public class RedPacketController extends BaseController {

	@Autowired
	RedPacketService redPacketService;
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/redPacket/list";
	}
	
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<RedPacketEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,RedPacketEntity redPacketEntity) {
		Page<RedPacketEntity> reslut=redPacketService.getPage(redPacketEntity, pageNumber, pageSize);
	    return reslut;
	}
	//修改
	@RequestMapping(MappingURL.MODIFY_URL)
	@ResponseBody
	public JsonResult modify(Model model,RedPacketEntity redPacketEntity) {
		JsonResult jsonResult=null;
		if(redPacketEntity.getId().equals("")||redPacketEntity.getId() == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "红包清算对象id为null"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		try {
			redPacketService.modifyRedPacket(redPacketEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
}
