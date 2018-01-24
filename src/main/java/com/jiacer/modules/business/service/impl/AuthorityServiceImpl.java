package com.jiacer.modules.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiacer.modules.business.service.AuthorityService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.CertAuthorityMapper;
import com.jiacer.modules.mybatis.entity.AuthorityEntity;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;
@Service
public class AuthorityServiceImpl extends BaseService implements AuthorityService{

	@Autowired
	CertAuthorityMapper certAuthorityDao;
	
	
	@Override
	public AuthorityEntity getById(Integer authorityId) {
		try {
			return certAuthorityDao.getById(authorityId);
		} catch (Exception e) {
			Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
		}
		return null;
	}
	
	@Override
	public Page<AuthorityEntity> getAuthorityPage(AuthorityEntity authorityEntity,int pageNumber, int pageSize){
		try {
			 //获取总条数
			Map<Object, Object>  map =new HashMap<Object, Object>();
			if(authorityEntity.getStartDate()!=null){
				map.put("startDate", DateUtils.joinTime(authorityEntity.getStartDate(),SysConstants.MIN_TIME));
			}
			if(authorityEntity.getEndDate()!=null){
				map.put("endDate", DateUtils.joinTime(authorityEntity.getEndDate(),SysConstants.MAX_TIME));
			}
			if(!StringUtils.isEmpty(authorityEntity.getStatus())){
				map.put("status", authorityEntity.getStatus());
			}
            Integer totalCount=certAuthorityDao.count(map);
            //分页实体
            Page<AuthorityEntity> page=new Page<AuthorityEntity>();
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
            	List<AuthorityEntity> list=certAuthorityDao.getPageList(map);
                page.setRows(list);
                page.setRecords(totalCount.longValue());
            }
            return page;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(String.format(Message.QUERY_ERROR_EXCEPTION, e));
			return null;
		}
	}
	
	
	
	@Override
	public void addAuthority(AuthorityEntity authorityEntity) throws Exception {
		
		authorityEntity.setAddAccount(UserUtils.getAccount());
		authorityEntity.setAddTime(new Date());
		
		certAuthorityDao.insertSelective(authorityEntity);
	}
	
	@Override
	public void modifyAuthority(AuthorityEntity authorityEntity) throws Exception {
		
			
				certAuthorityDao.updateByPrimaryKeySelective(authorityEntity);
	
	}
}
