package com.jiacer.modules.business.validate;

import com.jiacer.modules.business.bean.UserBean;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;

/** 
* @ClassName: UsersValidate 
* @Description: 会员管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:17:36 
*  
*/
public class UsersValidate {

	/**
	 * @param userBean
	 * @return
	 */
	public static JsonResult modifyValidate(UserBean userBean) {
		UserBaseInfoEntity userInfo=userBean.getUserInfo();
		UserExtendInfoEntity userExtend=userBean.getUserExtend();
		
		if(StringUtils.isEmpty(userInfo.getMobile())){
			return new JsonResult(false,"请填写手机号码",null);
		}
		
		if(!BaseValidate.isMobile(userInfo.getMobile())){
			return new JsonResult(false,"请填写正确的手机号码",null);
		}
		
		if(StringUtils.isEmpty(userExtend.getUserName())){
			return new JsonResult(false,"请填写用户姓名",null);
		}
		
		if(StringUtils.isEmpty(userExtend.getCertNo())){
			return new JsonResult(false,"请填写身份证",null);
		}

		if(!BaseValidate.isIdcard(userExtend.getCertNo())){
			return new JsonResult(false,"请填写正确的身份证",null);
		}
		
		if(StringUtils.isEmpty(userExtend.getEducation())){
			return new JsonResult(false,"请选择学历",null);
		}
		
		if(StringUtils.isEmpty(userExtend.getNation())){
			return new JsonResult(false,"请选择名族",null);
		}
		
		if(StringUtils.isEmpty(userExtend.getBirthplace())){
			return new JsonResult(false,"请选择籍贯",null);
		}
		
		if(StringUtils.isEmpty(userExtend.getAddress())){
			return new JsonResult(false,"请填写户籍地址",null);
		}
		
		if(StringUtils.isEmpty(userExtend.getSourceType())){
			return new JsonResult(false,"请填写选择来源",null);
		}
		
		if(StringUtils.isEmpty(userExtend.getSourceTypeSec())){
			return new JsonResult(false,"请填写选择来源",null);
		}
		
		if(StringUtils.isEmpty(userExtend.getSourceValue())){
			return new JsonResult(false,"请填写选择来源",null);
		}
		
		return new JsonResult(true,null,null);
	}

}
