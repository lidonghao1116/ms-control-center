package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.QuestionsEntity;

@MyBatisDao
public interface QuestionsMapper extends CrudDao<QuestionsEntity>{
	
 	/**
	 * @param map
	 * @return
	 */
	Integer count(Map<Object, Object> map);
	/**
	 * @param map
	 * @return
	 */
	List<QuestionsEntity> getPageList(Map<Object, Object> map);
	
	//AnswersEntity selectById (Integer id);
}