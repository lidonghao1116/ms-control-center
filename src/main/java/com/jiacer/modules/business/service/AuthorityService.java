package com.jiacer.modules.business.service;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.AuthorityEntity;

public interface AuthorityService {
	
	
	/**
	 * 根据id获取课程分类管理对象
	 * @param id
	 * @return
	 */
	AuthorityEntity getById(Integer authorityId);

	
	/**
	 * 发证机构管理分页
	 * @param authorityEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<AuthorityEntity> getAuthorityPage(AuthorityEntity authorityEntity, int pageNumber, int pageSize);

	/**
	 * 新增发证机构
	 * @param authorityEntity
	 * @throws Exception
	 */
	void addAuthority(AuthorityEntity authorityEntity) throws Exception;
	
	/**
	 * 修改发证机构
	 * @param authorityEntity
	 * @throws Exception
	 */
	void modifyAuthority(AuthorityEntity authorityEntity) throws Exception;
}
