package com.jiacer.modules.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiacer.modules.business.service.RedPacketService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.RedPacketMapper;
import com.jiacer.modules.mybatis.dao.UserBaseInfoMapper;
import com.jiacer.modules.mybatis.dao.UserExtendInfoMapper;
import com.jiacer.modules.mybatis.entity.AuthorityEntity;
import com.jiacer.modules.mybatis.entity.RedPacketEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;
@Service
public class RedPackectServiceImpl extends BaseService implements RedPacketService{

	@Autowired
	RedPacketMapper redPacketDao;
	
	@Autowired
	UserExtendInfoMapper userExtendDao;
	
	@Autowired
	UserBaseInfoMapper userBaseDao;
	
	@Override
	public Page<RedPacketEntity> getPage(RedPacketEntity redPacketEntity, int pageNumber, int pageSize) {
		try {
            //获取总条数
			Map<Object, Object>  map =new HashMap<Object, Object>();
			if(redPacketEntity.getStartDate()!=null){
				map.put("startDate", DateUtils.joinTime(redPacketEntity.getStartDate(),SysConstants.MIN_TIME));
			}
			if(redPacketEntity.getEndDate()!=null){
				map.put("endDate", DateUtils.joinTime(redPacketEntity.getEndDate(),SysConstants.MAX_TIME));
			}
			if(!StringUtils.isEmpty(redPacketEntity.getInviterName())){
				map.put("inviterName", redPacketEntity.getInviterName());
			}
			if(!StringUtils.isEmpty(redPacketEntity.getInviterOpenid())){
				map.put("inviterOpenid", redPacketEntity.getInviterOpenid());
			}
			if(!StringUtils.isEmpty(redPacketEntity.getStatus())){
				map.put("status", redPacketEntity.getStatus());
			}
			if(!StringUtils.isEmpty(redPacketEntity.getOrderNo())){
				map.put("orderNo", redPacketEntity.getOrderNo());
			}
			if(!StringUtils.isEmpty(redPacketEntity.getRedpackWxNo())){
				map.put("redpackWxNo", redPacketEntity.getRedpackWxNo());
			}
            Integer totalCount=redPacketDao.count(map);
            //分页实体
            Page<RedPacketEntity> page=new Page<RedPacketEntity>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);
            if(totalCount==null){
                return page;
            }
            //最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            if (totalCount > 0) {
            	map.put("offset",page.getOffset());
            	map.put("pageSize",page.getRowNum());
            	List<RedPacketEntity> list=redPacketDao.getRedPacketPageList(map);
            	
            	for (RedPacketEntity redPacket : list) {
            		UserBaseInfoEntity userBaseInfoEntity=userBaseDao.getById(redPacket.getInviterId()); 
            		UserExtendInfoEntity extendInfoEntity=userExtendDao.getById(redPacket.getInviterId());
            		
            		if(userBaseInfoEntity!=null){
            			redPacket.setUserBaseInfo(userBaseInfoEntity);
            		}
            		if(extendInfoEntity!=null){
            			redPacket.setUserExtendInfo(extendInfoEntity);
            		}
            	}
            	
                page.setRows(list);
                page.setRecords(totalCount.longValue());
            }
            return page;
        } catch (Exception e) {
        	e.printStackTrace();
        	Log.error(String.format(Message.QUERY_ERROR_EXCEPTION, e));
            return null;
        }
	}
	
	
	@Override
	public void modifyRedPacket(RedPacketEntity redPacketEntity) throws Exception {
		
			if (redPacketEntity.getId()!=null&&!redPacketEntity.getId().equals("")) {
				redPacketEntity.setId(redPacketEntity.getId());
				redPacketEntity.setStatus("02");
				redPacketEntity.setIsUsable("1");
				redPacketEntity.setModifyTime(new Date());
				redPacketEntity.setModifyAccount(UserUtils.getAccount());
				redPacketDao.updateById(redPacketEntity);
			}else {
				throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
			}
			
	}
}
