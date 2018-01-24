package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.VideosEntity;

@MyBatisDao
public interface VideosMapper extends CrudDao<VideosEntity>{
}