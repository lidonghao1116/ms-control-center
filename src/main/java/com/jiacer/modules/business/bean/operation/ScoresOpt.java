package com.jiacer.modules.business.bean.operation;

import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.UserScoresEntity;

/** 
* @ClassName: ScoresOpt 
* @Description: 成绩管理操作
* @author 贺章鹏
* @date 2016年10月24日 下午4:14:59 
*  
*/
public class ScoresOpt {
	/**
	 * 构建新增参数
	 * @param obj
	 * @return
	 */
	public static UserScoresEntity buildAdd(UserScoresEntity obj){
		return obj;
	}
	
	public static UserScoresEntity buildModify(UserScoresEntity obj,UserScoresEntity param){
		if(param.getTheoryScores()!=null){
			obj.setTheoryScores(param.getTheoryScores());
		}
		
		if(param.getPoScores()!=null){
			obj.setPoScores(param.getPoScores());
		}
		if(StringUtils.isEmpty(param.getExamResult())){
			obj.setExamResult(param.getExamResult());
		}
		if(StringUtils.isEmpty(param.getDealResult())){
			obj.setDealResult(param.getDealResult());
		}
		if(StringUtils.isEmpty(param.getCertificateNo())){
			obj.setCertificateNo(param.getCertificateNo());
		}
		if(param.getMakeupExamFree()!=null){
			obj.setMakeupExamFree(param.getMakeupExamFree());
		}
		return obj;
	}
}
