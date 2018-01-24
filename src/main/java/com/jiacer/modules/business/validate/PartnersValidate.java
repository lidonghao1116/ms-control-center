package com.jiacer.modules.business.validate;

import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.PartnersEntity;

/** 
* @ClassName: PartnersValidate 
* @Description: 合作商管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:16:46 
*  
*/
public class PartnersValidate {
	/**
	 * 新增方法参数校验
	 * @param partnersEntity
	 * @return
	 */
	public static JsonResult addValidate(PartnersEntity partnersEntity){
		
		if(StringUtils.isEmpty(partnersEntity.getPartnerType())){
			return new JsonResult(false,"请选择合作商类型",null);
		}
		
		if(StringUtils.isEmpty(partnersEntity.getPartnerName())){
			return new JsonResult(false,"请填写合作商名称",null);
		}
		
		if(StringUtils.isEmpty(partnersEntity.getProvince())
				|| StringUtils.isEmpty(partnersEntity.getCity())
				|| StringUtils.isEmpty(partnersEntity.getCounty())
				|| StringUtils.isEmpty(partnersEntity.getAddress())){
			return new JsonResult(false,"请填写合作商地址",null);
		}
		
		if(StringUtils.isEmpty(partnersEntity.getContacts())){
			return new JsonResult(false,"请填写联系人",null);
		}
		
		if(StringUtils.isEmpty(partnersEntity.getContactPhone())){
			return new JsonResult(false,"请填写联系人手机",null);
		}
		
		return new JsonResult(true,null,null);
	}
	
	/**
	 * 修改方法参数校验
	 * @param partnersEntity
	 * @return
	 */
	public static JsonResult modifyValidate(PartnersEntity partnersEntity){
		
		if(StringUtils.isEmpty(partnersEntity.getProvince())
				|| StringUtils.isEmpty(partnersEntity.getCity())
				|| StringUtils.isEmpty(partnersEntity.getCounty())
				|| StringUtils.isEmpty(partnersEntity.getAddress())){
			return new JsonResult(false,"请填写合作商地址",null);
		}
		
		if(StringUtils.isEmpty(partnersEntity.getContacts())){
			return new JsonResult(false,"请填写联系人",null);
		}
		
		if(StringUtils.isEmpty(partnersEntity.getContactPhone())){
			return new JsonResult(false,"请填写联系人手机",null);
		}
		
		return new JsonResult(true,null,null);
	}
	
}
