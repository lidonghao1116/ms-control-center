package com.jiacer.modules.mybatis.dao;

import java.util.List;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.model.InstitutionInfo;

@MyBatisDao
public interface InstitutionInfoMapper extends CrudDao<InstitutionInfo> {

	/**
	 * 
	 * @MethodName:getInstitutionId
	 * @Type:InstitutionInfoMapper
	 * @Description:获取Institution 表中所有的机构ID
	 * @Return:List<InstitutionInfo>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 11, 2017 6:00:56 PM
	 */
	List<InstitutionInfo> getInstitutionId();
    
}