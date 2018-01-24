package com.jiacer.modules.business.controller;

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

import com.google.common.collect.Lists;
import com.jiacer.modules.business.service.ScoresService;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.UserScoresEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;

/** 
* @ClassName: CertificateController 
* @Description: 分校管理--证书管理控制器
* @author zhangsq
* @date 
*  
*/
@Controller
@RequestMapping(MappingURL.CERTIFICATE)
public class CertificateController extends BaseController{

	
	@Resource
	ScoresService scoresService;
	
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/certificate/list";
	}
	
	//查询
	@SuppressWarnings("rawtypes")
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<Map> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,UserScoresEntity userScoresEntity) {
	    return  scoresService.getScoresPage(userScoresEntity, pageNumber, pageSize);
	}
	
	
	//详细页面
	@RequestMapping(MappingURL.INFO_URL)
	public String info(Model model,Integer schoolId,Integer classId,Integer userId ) {
		try {
			UserScoresEntity userScores=scoresService.getUserScores(schoolId,classId,userId);
			model.addAttribute("model", userScores);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
		}
		return "modules/certificate/detail";
	}
	
	/*@RequestMapping(MappingURL.EXPORT)
	public String export(Model model,HttpServletResponse response,HttpServletRequest request,@PathVariable String type
			,Integer id) {
		
		if(StringUtils.isEmpty(type) || id==null){
			model.addAttribute("message", "参数错误");
		}else{
			scoresService.dealExportScores(model,id,response,request);
		}
	    return "modules/excel/info";
	}*/
}
