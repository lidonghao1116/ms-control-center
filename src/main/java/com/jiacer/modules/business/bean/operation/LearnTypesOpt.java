package com.jiacer.modules.business.bean.operation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.iterators.ObjectArrayIterator;

import com.google.common.collect.Maps;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.CourseInfoEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.TemplateInfoEntity;
import com.jiacer.modules.mybatis.model.CourseInfo;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: LearnTypesQuery 
* @Description: 课程管理构建参数操作
* @author 贺章鹏
* @date 2016年10月24日 上午11:15:52 
*  
*/
public class LearnTypesOpt {
	
	/**
	 * 构建查询参数Map
	 * @param learnTypesEntity
	 * @return
	 */
	public static Map<Object, Object> buildMap(CourseInfoEntity courseInfoEntity){
		Map<Object, Object> map=Maps.newHashMap();
		if(!StringUtils.isEmpty(courseInfoEntity.getCourseName())){
			map.put("courseName", SqlUtils.like(courseInfoEntity.getCourseName()));
		}
		if(!StringUtils.isEmpty(courseInfoEntity.getStatus())){
			map.put("status", courseInfoEntity.getStatus());
		}
		//map.put("isUsable", DBStatus.IsUsable.TRUE);
		return map;
	}
	
	/**
	 * 构建新增参数
	 * @param obj
	 * @return
	 */
	public static CourseInfoEntity buildAdd(CourseInfoEntity obj){
		
		//obj.setIsUsable(DBStatus.IsUsable.TRUE);
		obj.setAddAccount(UserUtils.getAccount());
		obj.setAddTime(new Date());
		obj.setCreatTime(new Date());
		//obj.setStatus(DBStatus.CourseStatus.NOMAL);
		return obj;
	}
	
	/**
	 * 建构修改参数
	 * @param obj
	 * @param param
	 * @return
	 */
	public static CourseInfoEntity buildUpdate(CourseInfoEntity obj,CourseInfoEntity param){
		
		obj.setCourseName(param.getCourseName());
		obj.setFitService(param.getFitService());
		obj.setPrice(param.getPrice()!=null?param.getPrice():BigDecimal.ZERO);
		obj.setSortNo(param.getSortNo());
		obj.setCourseType(param.getCourseType());
		obj.setSummary(StringUtils.isEmpty(param.getSummary().trim())?"":param.getSummary());
		obj.setStatus(param.getStatus());
		obj.setReSign(param.getReSign());
		obj.setCourseTime(param.getCourseTime());
		obj.setModifyTime(new Date());
		obj.setModifyAccount(UserUtils.getAccount());
		return obj;
	}
	

}
