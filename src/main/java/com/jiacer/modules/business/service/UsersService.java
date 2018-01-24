package com.jiacer.modules.business.service;

import com.jiacer.modules.business.bean.UserBean;
import com.jiacer.modules.business.bean.form.UsersQuery;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;

/** 
* @ClassName: UsersService 
* @Description: 会员用户管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:07:09 
*  
*/
public interface UsersService {

	/**
	 * 学员信息分页
	 * @param users
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<UserBaseInfoEntity> getUsersPage(UsersQuery usersQuery, int pageNumber, int pageSize);

	/**
	 * 获取用户的详情根据id
	 * @param id
	 * @return
	 */
	UserBean getUsersInfo(Integer id);

	/**
	 * 获取会员信息
	 * @param id
	 * @return
	 */
	UserBean getUsers(Integer id);


}
