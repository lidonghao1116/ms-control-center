package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;

@MyBatisDao
public interface UserBaseInfoMapper extends CrudDao<UserBaseInfoEntity>{

	/**
	 * @param map
	 * @return
	 */
	Integer count(Map<Object, Object> map);

	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	List<UserBaseInfoEntity> getPageList(Map<Object, Object> map);
	
	UserBaseInfoEntity getByInviterCode(String inviterCode);
}