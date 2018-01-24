package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;

@MyBatisDao
public interface ApplyOrdersMapper extends CrudDao<ApplyOrdersEntity>{

	/**
	 * @param map
	 * @return
	 */
	Integer count(Map<Object, Object> map);

	/**
	 * @param map
	 * @return
	 */
	List<ApplyOrdersEntity> getPageList(Map<Object, Object> map);

	/**
	 * 获取个数
	 * @param entity
	 * @return
	 */
	Integer findAllCount(ApplyOrdersEntity entity);
}