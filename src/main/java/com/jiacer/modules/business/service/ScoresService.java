package com.jiacer.modules.business.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.UserScoresEntity;
import com.jiacer.modules.mybatis.entity.extend.LearnRecordEntity;

/** 
* @ClassName: ExamsService 
* @Description: 考试管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:06:20 
*  
*/
public interface ScoresService {

	/**
	 * 成绩管理分页
	 * @param examClassEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<Map> getScoresPage(UserScoresEntity userScoresEntity, int pageNumber, int pageSize);

	

	/**
	 * 获取用户成绩实体
	 * @param userScoresEntity
	 * @return
	 */
	UserScoresEntity getUserScores(Integer schoolId, Integer classId,Integer userId);

	/**
	 * 获取详情信息
	 * @param id
	 * @return
	 *//*
	ExamClassEntity getExamClassInfo(Integer id);

	*//**
	 * @param model
	 * @param id
	 * @param response
	 *//*
	Model dealExport(Model model, Integer id, HttpServletResponse response,HttpServletRequest request);

	*//**
	 * @param model
	 * @param id
	 * @param response
	 * @param request
	 *//*
	Model dealExportScores(Model model, Integer id, HttpServletResponse response, HttpServletRequest request);

	*//**
	 * @param id
	 * @return
	 *//*
	List<LearnRecordEntity> getExamLearnRecords(Integer id);

	*//**
	 * @param classId
	 * @param model
	 * @param response
	 * @param request
	 * @return
	 *//*
	Model exportExamLearnRecords(Integer classId, Model model, HttpServletResponse response,
			HttpServletRequest request);
*/
}
