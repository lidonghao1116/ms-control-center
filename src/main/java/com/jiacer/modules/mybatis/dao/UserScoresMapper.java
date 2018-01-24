package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.UserCertEntity;
import com.jiacer.modules.mybatis.entity.UserScoresEntity;
import com.jiacer.modules.mybatis.model.UserScoresKey;

@MyBatisDao
public interface UserScoresMapper extends CrudDao<UserScoresEntity>{

	/**
	 * @param userScoresKey
	 */
	UserScoresEntity getByKey(UserScoresKey userScoresKey);
	
	
	/**
	 * @param map
	 * @return
	 */
	Integer count(Map<Object, Object> map);

	/**
	 * @param map
	 * @return
	 */
	List<Map> getPageList(Map<Object, Object> map);


    UserCertEntity getCertInfoByUserAndClass(UserScoresKey userScoresKey);
}