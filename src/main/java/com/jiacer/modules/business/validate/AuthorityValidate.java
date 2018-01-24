package com.jiacer.modules.business.validate;

import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.AuthorityEntity;

public class AuthorityValidate {

	/**
	 * 新增方法参数校验
	 * @param authorityEntity
	 * @return
	 */
	public static JsonResult addValidate(AuthorityEntity authorityEntity){
		
		if(StringUtils.isEmpty(authorityEntity.getAuthorityName())){
			return new JsonResult(false,"请填写发证机构名称",null);
		}
		
		if(authorityEntity.getStatus()==null){
			return new JsonResult(false,"请填写是否上架",null);
		}
		return new JsonResult(true,null,null);
	}
	
}
