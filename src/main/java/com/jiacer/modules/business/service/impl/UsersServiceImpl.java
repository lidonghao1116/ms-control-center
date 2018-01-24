package com.jiacer.modules.business.service.impl;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiacer.modules.business.bean.IdcardInfoExtractor;
import com.jiacer.modules.business.bean.UserBean;
import com.jiacer.modules.business.bean.form.UsersQuery;
import com.jiacer.modules.business.bean.operation.UsersOpt;
import com.jiacer.modules.business.service.UsersService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.ApplyOrdersMapper;
import com.jiacer.modules.mybatis.dao.ExamClassMapper;
import com.jiacer.modules.mybatis.dao.UserBaseInfoMapper;
import com.jiacer.modules.mybatis.dao.UserExtendInfoMapper;
import com.jiacer.modules.mybatis.dao.UserScoresMapper;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.system.config.Message;

/** 
* @ClassName: UsersServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年10月19日 下午4:20:37 
*  
*/
@Service
public class UsersServiceImpl extends BaseService implements UsersService {
	
	@Autowired
	UserBaseInfoMapper userBaseInfoDao;
	
	@Autowired
	UserExtendInfoMapper userExtendInfoDao;
	
	@Autowired
	ApplyOrdersMapper applyOrdersDao;
	
	@Autowired
	ExamClassMapper examClassDao;
	
	@Autowired
	UserScoresMapper userScoresDao;

	@Override
	public Page<UserBaseInfoEntity> getUsersPage(UsersQuery usersQuery, int pageNumber, int pageSize) {
		try {
            //获取总条数
			Map<Object, Object>  map=UsersOpt.buildMap(usersQuery);
            Integer totalCount=userBaseInfoDao.count(map);
            //分页实体
            Page<UserBaseInfoEntity> page=new Page<UserBaseInfoEntity>();
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
            	List<UserBaseInfoEntity> list=userBaseInfoDao.getPageList(map);
            	UserExtendInfoEntity userExtendInfo=null;
            	for(UserBaseInfoEntity userBean:list){
            		userExtendInfo=new UserExtendInfoEntity();
            		userExtendInfo=userExtendInfoDao.getById(userBean.getId());
            		if(userExtendInfo!=null){
            			userBean.setUserExtend(userExtendInfo);
            		}
            		//检查认证状态  : 有手机号，没有微信ID  认证状态 1为已注册
            		if((StringUtils.isNotBlank(userBean.getMobile()) && StringUtils.isBlank(userBean.getOpenId()))){
            			userBean.setAuthStatus("1");
            		}else
            		//检查认证状态  : 有手机号，有微信ID，没有身份证      微信昵称为空  认证状态 2为已绑定
					if((StringUtils.isNotBlank(userBean.getMobile()) && StringUtils.isNotBlank(userBean.getOpenId())) && StringUtils.isBlank(userBean.getCertNo())){
            			userBean.setAuthStatus("2");
					}else
            		//检查认证状态  : 有手机号，有微信I，有身份证      微信昵称不为空  认证状态 3为已认证
					if((StringUtils.isNotBlank(userBean.getMobile()) && StringUtils.isNotBlank(userBean.getOpenId())) && StringUtils.isNotBlank(userBean.getCertNo())){
            			userBean.setAuthStatus("3");
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
	public UserBean getUsersInfo(Integer id) {
		UserBean user=new UserBean();
		try {
			UserBaseInfoEntity userInfo=userBaseInfoDao.getById(id);
			UserExtendInfoEntity userExtend=userExtendInfoDao.getById(id);
			
			if(userInfo!=null){
				user.setUserInfo(userInfo);
			}
			if(userExtend!=null){
				user.setUserExtend(userExtend);
			}
			if (userInfo.getInviterCode()!=null&&!userInfo.getInviterCode().equals("")) {
				UserBaseInfoEntity inviterbaseInfo = userBaseInfoDao.getByInviterCode(userInfo.getInviterCode());
				if (inviterbaseInfo!=null) {
					user.setInviterInfo(inviterbaseInfo);
				}
			}
		} catch (Exception e) {
			Log.error("获取用户详细失败");
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public UserBean getUsers(Integer id) {
		UserBean user=new UserBean();
		try {
			UserBaseInfoEntity userInfo=userBaseInfoDao.getById(id);
			UserExtendInfoEntity userExtend=userExtendInfoDao.getById(id);
			if(userInfo!=null){
				user.setUserInfo(userInfo);
			}else{
				user.setUserInfo(new UserBaseInfoEntity());
			}
			if(userExtend!=null){
				IdcardInfoExtractor idcardInfo=new IdcardInfoExtractor(userExtend.getCertNo()); 
				userExtend.setAge(idcardInfo.getAge());
				user.setUserExtend(userExtend);
			}else{
				user.setUserExtend(new UserExtendInfoEntity());
			}
		} catch (Exception e) {
			Log.error("获取用户信息失败");
			e.printStackTrace();
		}
		return user;
	}


}
