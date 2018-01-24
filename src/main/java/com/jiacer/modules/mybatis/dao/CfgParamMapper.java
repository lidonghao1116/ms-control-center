package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;
import com.jiacer.modules.mybatis.model.CfgParam;

@MyBatisDao
public interface CfgParamMapper extends CrudDao<CfgParamEntity>{
	
	/**
	 * 
	 * @MethodName:getTextByValue
	 * @Type:CfgParamMapper
	 * @Description:获取参数列表TEXT值
	 * @Return:String
	 * @Param:@param map
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 26, 2017 6:36:56 PM
	 */
	String getTextByValue(Map<Object, Object> map);
	
	/**
	 * 
	 * @param courseSheleves 
	 * @MethodName:getCfgParam
	 * @Type:CfgParamMapper
	 * @Description:获取参数信息
	 * @Return:List<CfgParam>
	 * @Param:@param key
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 28, 2017 2:53:04 PM
	 */
	List<CfgParam> getCfgParamOfValue(String value);
}