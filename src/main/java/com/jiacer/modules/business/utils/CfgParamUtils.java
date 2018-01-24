package com.jiacer.modules.business.utils;

import java.io.Serializable;
import java.util.List;

import com.jiacer.modules.common.constants.Constants;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.mybatis.dao.CfgParamMapper;
import com.jiacer.modules.mybatis.model.CfgParam;

/**
 * 
 * @ClassName:CfgParamUtils.java
 * @Description:参数列表信息
 * @Author:ticahmfock
 * @Date:Sep 27, 2017 6:21:10 PM
 */
public class CfgParamUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static CfgParamMapper cfgParamMapper = SpringContextHolder.getBean(CfgParamMapper.class);
	
	/**
	 * 
	 * @MethodName:getCfgParamofGrade
	 * @Type:CfgParamUtils
	 * @Description:获取鉴定等级信息
	 * @Return:List<CfgParam>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 28, 2017 3:46:10 PM
	 */
	public static List<CfgParam> getCfgParamofGrade() {
		List<CfgParam> list = cfgParamMapper.getCfgParamOfValue(Constants.AUTHORITY_GRADE);
		return list;
	}
	
	/**
	 * 
	 * @MethodName:getCfgParamOfStatus
	 * @Type:CfgParamUtils
	 * @Description:获取课程状态信息
	 * @Return:List<CfgParam>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 28, 2017 3:50:04 PM
	 */
	public static List<CfgParam> getCfgParamOfStatus() {
		List<CfgParam> list = cfgParamMapper.getCfgParamOfValue(Constants.COURSE_SHELEVES);
		return list;
	}

	/**
	 * 
	 * @MethodName:getCfgParamOfExamType
	 * @Type:CfgParamUtils
	 * @Description:考试形式
	 * @Return:List<CfgParam>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 29, 2017 6:59:53 AM
	 */
	public static List<CfgParam> getCfgParamOfExamType() {
		List<CfgParam> list = cfgParamMapper.getCfgParamOfValue(Constants.EXAM_TYPE);
		return list;
	}
}
