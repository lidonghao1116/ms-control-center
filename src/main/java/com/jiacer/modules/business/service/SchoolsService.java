package com.jiacer.modules.business.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jiacer.modules.business.bean.form.SchoolsForm;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;

/** 
* @ClassName: schoolsService 
* @Description: 学校管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:06:49 
*  
*/
public interface SchoolsService {

	/**
	 * 根据id获取学校对象
	 * @param id
	 * @return
	 */
	SchoolsEntity getSchoolsById(Integer id);

	/**
	 * 学校对象分页
	 * @param schoolsEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<SchoolsEntity> getSchoolsPage(SchoolsEntity schoolsEntity, int pageNumber, int pageSize);

	/**
	 * 添加学校
	 * @param schoolsEntity
	 * @throws Exception
	 */
	JsonResult addschools(SchoolsForm schoolsEntity) throws Exception;

	/**
	 * 修改学校
	 * @param schoolsEntity
	 * @throws Exception
	 */
	void modifyschools(SchoolsForm schoolsEntity) throws Exception;

	/**
	 * 删除学校
	 * @param schoolsEntity
	 * @throws Exception
	 */
	void delschools(SchoolsEntity schoolsEntity) throws Exception;

	/**
	 * check
	 * @param loginAccount
	 * @throws Exception
	 */
	JsonResult check(String loginAccount) throws Exception;


    void resetPwd(SchoolsForm schoolsEntity);
}
