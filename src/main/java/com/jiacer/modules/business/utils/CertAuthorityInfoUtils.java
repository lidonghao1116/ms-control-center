package com.jiacer.modules.business.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.CertAuthorityMapper;
import com.jiacer.modules.mybatis.entity.AuthorityEntity;

/**
 * 
 * @ClassName:AuthorityInfoUtils.java
 * @Description:发证机构信息缓存Utils
 * @Author:ticahmfock
 * @Date:Sep 27, 2017 5:28:38 PM
 */
public class CertAuthorityInfoUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static CertAuthorityMapper certAuthorityMapper = SpringContextHolder.getBean(CertAuthorityMapper.class);

	/**
	 * 
	 * @MethodName:getCertAuthorityInfo
	 * @Type:CertAuthorityInfoUtils
	 * @Description:获取发证机构基础信息
	 * @Return:List<AuthorityEntity>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 27, 2017 5:33:34 PM
	 */
	public static List<AuthorityEntity> getCertAuthorityInfo() {
		List<AuthorityEntity> list = new ArrayList<>();
		try {
			list= certAuthorityMapper.getAllCertAuthority();
		} catch (Exception e) {
			Log.error("获取发证机构信息失败");
		}
		return list;
	}
	
	
	
}
