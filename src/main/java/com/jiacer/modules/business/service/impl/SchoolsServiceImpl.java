package com.jiacer.modules.business.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jiacer.modules.business.bean.form.SchoolsForm;
import com.jiacer.modules.business.bean.operation.SchoolsOpt;
import com.jiacer.modules.business.service.SchoolsService;
import com.jiacer.modules.business.utils.SchoolsUtils;
import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.common.utils.EntryptUtils;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.ResultCode;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.SchoolsMapper;
import com.jiacer.modules.mybatis.dao.SysUserRoleMapper;
import com.jiacer.modules.mybatis.dao.SysUsersMapper;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.mybatis.model.SysUserRole;
import com.jiacer.modules.mybatis.model.SysUsers;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.UserUtils;

import javax.jws.soap.SOAPBinding;

/**
 * @author 贺章鹏
 * @ClassName: schoolsServiceImpl
 * @Description: TODO
 * @date 2016年10月19日 下午4:20:25
 */
@Service
public class SchoolsServiceImpl extends BaseService implements SchoolsService {

    @Autowired
    SchoolsMapper schoolsDao;

    @Autowired
    SysUsersMapper sysUsersMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleDao;

    @Override
    public SchoolsEntity getSchoolsById(Integer id) {
        try {
            SchoolsEntity schools = schoolsDao.getById(id);
            SysUsersEntity sysUsersEntity = new SysUsersEntity();
            sysUsersEntity.setSchoolId(schools.getId());
            sysUsersEntity.setIsUsable(DBStatus.IsUsable.TRUE);
            sysUsersEntity.setIsInit("1");
            SysUsersEntity SysUsers = sysUsersMapper.get(sysUsersEntity);
            if (SysUsers != null) {
                schools.setSysUsers(SysUsers);
            }
            return schools;
        } catch (Exception e) {
            Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
        }
        return null;
    }

    @Override
    public Page<SchoolsEntity> getSchoolsPage(SchoolsEntity schoolsEntity, int pageNumber, int pageSize) {
        try {
            //获取总条数
            Map<Object, Object> map = SchoolsOpt.buildMap(schoolsEntity);
            Integer totalCount = schoolsDao.count(map);
            //分页实体
            Page<SchoolsEntity> page = new Page<SchoolsEntity>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);
            if (totalCount == null) {
                return page;
            }
            //最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            if (totalCount > 0) {
                map.put("offset", page.getOffset());
                map.put("pageSize", page.getRowNum());
                List<SchoolsEntity> list = schoolsDao.getPageList(map);

            	/*for (SchoolsEntity entity : list) {
					if (!entity.getPrivince().equals("")&&entity.getPrivince()!=null) {
						
					}
				}*/

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
    public JsonResult addschools(SchoolsForm schoolsEntity) throws Exception {
        //学校基本信息
        SchoolsEntity schools = new SchoolsEntity();

        schools.setIsUsable(DBStatus.IsUsable.TRUE);
        schools.setAddAccount(UserUtils.getAccount());

        schools.setSchoolName(schoolsEntity.getSchoolName());
        schools.setPrivince(schoolsEntity.getPrivince());
        schools.setCity(schoolsEntity.getCity());
        schools.setArea(schoolsEntity.getArea());
        schools.setSchoolAddress(schoolsEntity.getSchoolAddress());
        schools.setSchoolPhone(schoolsEntity.getSchoolPhone());
        schools.setContacts(schoolsEntity.getContacts());
        schools.setContactPhone(schoolsEntity.getContactPhone());
        schools.setCompanyName(schoolsEntity.getCompanyName());
        schools.setLicenceNo(schoolsEntity.getLicenceNo());
        schools.setAgentName(schoolsEntity.getAgentName());
        schools.setAgentIdNumber(schoolsEntity.getAgentIdNumber());
        schools.setSaler(schoolsEntity.getSaler());
        schools.setAppltTime(new Date());
        schools.setAddTime(new Date());
        schools.setAddAccount(UserUtils.getUser().getLoginAccount());
        schools.setIdcardBackImg(schoolsEntity.getIdcardBackImg());
        schools.setIdcardFrontImg(schoolsEntity.getIdcardFrontImg());
        schools.setLicenceImg(schoolsEntity.getLicenceImg());
        schoolsDao.insertSelective(schools);

        //学校账号
        SysUsersEntity sysUsers = new SysUsersEntity();
        sysUsers.setIsUsable(DBStatus.IsUsable.TRUE);
        sysUsers.setAddAccount(UserUtils.getAccount());

        sysUsers.setUserType("02");
        sysUsers.setLoginAccount(schoolsEntity.getLoginAccount());
        sysUsers.setAddAccount(UserUtils.getUser().getLoginAccount());
        sysUsers.setAddTime(new Date());
        sysUsers.setMobile(schoolsEntity.getContactPhone());
        sysUsers.setTelephone(schoolsEntity.getContactPhone());
        sysUsers.setStartDate(new Date());
        sysUsers.setSalt("123456");
        sysUsers.setLoginName("管理员");
        sysUsers.setIsInit("1");
        sysUsers.setSchoolId(schools.getId());
        sysUsers.setPassword(EntryptUtils.entryptPassword("000000"));
        sysUsers.setLoginFlag("1");
        sysUsersMapper.insert(sysUsers);
        SysUsersEntity user=sysUsersMapper.get(sysUsers);
        for(int i=1;i<6;i++)
        {
        	SysUserRole userRole=new SysUserRole();
        	userRole.setUid(user.getId());
        	userRole.setRid(i);
        	sysUserRoleDao.insert(userRole);
        }
        
        SchoolsUtils.flush();
        return JsonResult.success(ResultCode.SUCCESS);
    }


    @Override
    public void modifyschools(SchoolsForm schoolsEntity) throws Exception {
        SchoolsEntity bean = this.getSchoolsById(schoolsEntity.getId());
        if (bean == null) {
            throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
        } else {
            SchoolsEntity schools = new SchoolsEntity();
            schools.setId(schoolsEntity.getId());
            schools.setSchoolName(schoolsEntity.getSchoolName());
            schools.setPrivince(schoolsEntity.getPrivince());
            schools.setCity(schoolsEntity.getCity());
            schools.setArea(schoolsEntity.getArea());
            schools.setSchoolAddress(schoolsEntity.getSchoolAddress());
            schools.setSchoolPhone(schoolsEntity.getSchoolPhone());
            schools.setContacts(schoolsEntity.getContacts());
            schools.setContactPhone(schoolsEntity.getContactPhone());
            schools.setCompanyName(schoolsEntity.getCompanyName());
            schools.setLicenceNo(schoolsEntity.getLicenceNo());
            schools.setAgentName(schoolsEntity.getAgentName());
            schools.setAgentIdNumber(schoolsEntity.getAgentIdNumber());
            schools.setSaler(schoolsEntity.getSaler());
            schools.setModifyAccount(UserUtils.getUser().getLoginAccount());
            schools.setLicenceImg(StringUtils.isNotBlank(schoolsEntity.getLicenceImg())? schoolsEntity.getLicenceImg():null);
            schools.setIdcardBackImg(StringUtils.isNotBlank(schoolsEntity.getIdcardBackImg())? schoolsEntity.getIdcardBackImg(): null);
            schools.setIdcardFrontImg(StringUtils.isNotBlank(schoolsEntity.getIdcardFrontImg())? schoolsEntity.getIdcardFrontImg():null);
            schoolsDao.updateByPrimaryKeySelective(schools);

            //学校账号
            SysUsersEntity sysUsers = new SysUsersEntity();
            sysUsers.setId(bean.getSysUsers().getId());
            sysUsers.setIsInit("1");
            sysUsers.setTelephone(schoolsEntity.getContactPhone());
            sysUsers.setModifyAccount(UserUtils.getUser().getLoginAccount());
            sysUsers.setModifyTime(new Date());
            sysUsersMapper.update(sysUsers);

        }
        SchoolsUtils.flush();
    }

    @Override
    public void delschools(SchoolsEntity schoolsEntity) throws Exception {
        SchoolsEntity bean = this.getSchoolsById(schoolsEntity.getId());
        if (bean == null) {
            throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
        } else {
            schoolsDao.update(SchoolsOpt.buildDelete(bean));
            sysUserRoleDao.deleteByUid(bean.getId());
        }
    }

    @Override
    public JsonResult check(String loginAccount) throws Exception {

        SysUsersEntity sysUsersEntity = new SysUsersEntity();
        sysUsersEntity.setLoginAccount(loginAccount);
        SysUsersEntity sysUsers = sysUsersMapper.get(sysUsersEntity);
        if (sysUsers != null) {
            return JsonResult.failure(ResultCode.schools.LOGIN_ACCOUNT_EXIT);
        }
        return JsonResult.success(ResultCode.SUCCESS);
    }

    @Override
    public void resetPwd(SchoolsForm schoolsEntity) {
        SysUsersEntity user = new SysUsersEntity();
        user.setSchoolId(schoolsEntity.getId());
        user.setIsInit("1");
        user.setPassword(EntryptUtils.entryptPassword("000000"));
        user.setModifyTime(new Date());
        user.setModifyAccount(UserUtils.getUser().getLoginAccount());
        sysUsersMapper.update(user);
    }

    public static void main(String[] args) {
        System.out.println(EntryptUtils.entryptUserPassword("000000", "123456"));
    }
}
