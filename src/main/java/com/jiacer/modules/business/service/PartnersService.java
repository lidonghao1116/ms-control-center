package com.jiacer.modules.business.service;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.PartnersEntity;

/** 
* @ClassName: PartnersService 
* @Description: 合作商接口管理服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:06:34 
*  
*/
public interface PartnersService {

	/**
	 * 根据id获取合作商对象
	 * @param id
	 * @return
	 */
	PartnersEntity getPartnersById(Integer id);

	/**
	 * 合作商分页查询
	 * @param partnersEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<PartnersEntity> getPartnersPage(PartnersEntity partnersEntity, int pageNumber, int pageSize);

	/**
	 * 新增合作商
	 * @param partnersEntity
	 * @throws Exception
	 */
	void addPartners(PartnersEntity partnersEntity) throws Exception;

	/**
	 * 修改合作商
	 * @param partnersEntity
	 * @throws Exception
	 */
	void modifyPartners(PartnersEntity partnersEntity) throws Exception;

	/**
	 * 删除合作商
	 * @param partnersEntity
	 * @throws Exception
	 */
	void delPartner(PartnersEntity partnersEntity) throws Exception;
	
	

}
