package com.jiacer.modules.business.service;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.RedPacketEntity;

public interface RedPacketService {

	
	/**
	 * @param redPacketEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<RedPacketEntity> getPage(RedPacketEntity redPacketEntity, int pageNumber, int pageSize);
	
	
	/**
	 * 修改发证机构
	 * @param redPacketEntity
	 * @throws Exception
	 */
	void modifyRedPacket(RedPacketEntity redPacketEntity) throws Exception;
}
