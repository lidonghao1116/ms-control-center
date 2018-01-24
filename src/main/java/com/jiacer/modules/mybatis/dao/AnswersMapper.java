package com.jiacer.modules.mybatis.dao;

import java.util.*;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.AnswersEntity;

@MyBatisDao
public interface AnswersMapper extends CrudDao<AnswersEntity>{
	
	
	
	List selectByQuestionId (Integer questionsId);
}