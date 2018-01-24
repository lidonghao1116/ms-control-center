package com.jiacer.modules.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.utils.EntryptUtils;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.SysUserRoleMapper;
import com.jiacer.modules.mybatis.dao.SysUsersMapper;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.mybatis.model.SysUserRole;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.service.SystemService;
import com.jiacer.modules.system.utils.UserUtils;

/**
 * @Description: 系统服务实现类
 * @author hzp
 * @date 2016-4-8
 *
 */
@Service
public class SystemServiceImpl extends BaseService implements SystemService{

	//*********************************系统管理用户*******************************************//
	@Autowired
	SysUsersMapper sysUsersDao;
	
	
	@Override
	@Transactional(readOnly=true)
	public SysUsersEntity getUseByLoginAccount(String account) {
		SysUsersEntity usersEntity=new SysUsersEntity();
		usersEntity.setLoginAccount(account);
		return sysUsersDao.get(usersEntity);
	}

	@Override
	@Transactional(readOnly=true)
	public SysUsersEntity getUseById(Integer id) {
		return sysUsersDao.getById(id);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addUser(SysUsersEntity user) throws Exception {
		user.setPassword(EntryptUtils.entryptPassword("000000"));
		sysUsersDao.insert(user);
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void modifyUser(SysUsersEntity user) throws Exception {
		sysUsersDao.update(user);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delUser(SysUsersEntity user) throws Exception{
		user.setModifyAccount(UserUtils.getAccount());
		user.setModifyTime(new Date());
		user.setIsUsable(DBStatus.IsUsable.TRUE);
		sysUsersDao.update(user);
	}

	@Override
	public Page<SysUsersEntity> getUserPage(SysUsersEntity userEntity,
			int pageNumber, int pageSize) {
		try {
            //获取总条数
			Map<Object, Object> map=new HashMap<Object, Object>();
			if(!StringUtils.isEmpty(userEntity.getLoginAccount())){
				map.put("loginAccount", userEntity.getLoginAccount());
			}
			if(!StringUtils.isEmpty(userEntity.getLoginName())){
				map.put("loginName", SqlUtils.like(userEntity.getLoginName()));
			}
			map.put("isUsable", DBStatus.IsUsable.TRUE);
			
            Integer totalCount=sysUsersDao.count(map);
            //分页实体
            Page<SysUsersEntity> page=new Page<SysUsersEntity>();
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
            	List<SysUsersEntity> list=sysUsersDao.getPageList(map);
                page.setRows(list);
                page.setRecords(totalCount.longValue());
            }
            return page;
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(String.format(Message.QUERY_ERROR_EXCEPTION, e));
            return null;
        }
	}

	@Override
	public void modifyPasswordById(Integer id, String newPassword) throws Exception{
		SysUsersEntity record=new SysUsersEntity();
		record.setId(id);
		record.setPassword(EntryptUtils.entryptPassword(newPassword));
		//修改用户信息
		int result=sysUsersDao.update(record);
		if(result==0){
			logger.error("用户修改密码sql异常");
			throw new RuntimeException("用户修改密码异常");
		}
	}

	
	//*********************************系统管理用户结束*******************************************//
	
	//*********************************字典参数*******************************************//
	@Override
	public CfgParamEntity getParamById(Integer id) {
		return null;
	}

	@Override
	public Page<CfgParamEntity> getParamsPage(CfgParamEntity cfgParamEntity, int pageNumber, int pageSize) {
		return null;
	}

	@Override
	public void addParams(CfgParamEntity cfgParamEntity) throws Exception {
		
	}

	@Override
	public void delParams(CfgParamEntity cfgParamEntity) throws Exception {
		
	}

	@Override
	public void modifyParams(CfgParamEntity cfgParamEntity) throws Exception {
		
	}
	//*********************************字典参数结束*******************************************//
}
