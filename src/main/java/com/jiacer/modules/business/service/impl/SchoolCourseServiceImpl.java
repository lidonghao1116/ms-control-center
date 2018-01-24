package com.jiacer.modules.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.jiacer.modules.business.service.LearnRecordsService;
import com.jiacer.modules.mybatis.dao.*;
import com.jiacer.modules.mybatis.entity.CourseBaseInfoEntity;
import com.jiacer.modules.mybatis.model.*;
import com.jiacer.modules.system.utils.ParamsUtils;
import com.jiacer.modules.system.utils.UserUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiacer.modules.business.bean.form.CourseBaseInfoForm;
import com.jiacer.modules.business.service.SchoolCourseService;
import com.jiacer.modules.common.constants.Constants;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.system.config.Message;

@Service
public class SchoolCourseServiceImpl extends BaseService implements SchoolCourseService {

    @Autowired
    LearnTypesMapper learnTypesDao;


    @Autowired
    CertAuthorityMapper certAuthorityDao;

    @Autowired
    CoursesBaseInfoMapper coursesBaseInfoMapper;

    @Autowired
    CfgParamMapper cfgParamMapper;

    @Autowired
    InstitutionInfoMapper institutionInfoMapper;

    @Autowired
    CourseInstitutionMapper courseInstitutionMapper;

    @Autowired
    CoursePackageMapper coursePackageMapper;

    /**
     * 总控--分校管理--课程管理--查询
     */
    @Override
    public Page<CourseBaseInfoEntity> getSchoolCoursePage(CourseBaseInfoEntity courseBaseInfoEntity, int pageNumber,
                                                          int pageSize) {

        try {
            Map<Object, Object> map = new HashMap<Object, Object>();
            if (courseBaseInfoEntity.getCourseName() != null
                    && !"---请选择---".equals(courseBaseInfoEntity.getCourseName())) {
                map.put("courseName", courseBaseInfoEntity.getCourseName());
            }
            if (courseBaseInfoEntity.getAuthenticateGrade() != null
                    && !"".equals(courseBaseInfoEntity.getAuthenticateGrade())) {
                map.put("authenticateGrade", courseBaseInfoEntity.getAuthenticateGrade());
            }
            if (courseBaseInfoEntity.getStatus() != null
                    && !"".equals(courseBaseInfoEntity.getStatus())) {
                map.put("status", courseBaseInfoEntity.getStatus());
            }
            if (courseBaseInfoEntity.getAuthorityId() != null) {
                map.put("authorityId", courseBaseInfoEntity.getAuthorityId());
            }
            Integer totalCount = coursesBaseInfoMapper.getCourseBaseInfoCount(map);


            //分页实体
            Page<CourseBaseInfoEntity> page = new Page<CourseBaseInfoEntity>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);

            if (totalCount == null) {
                return page;
            }

            //最大页数判断
            int pagM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pagM > 0) {
                page.setPage(pagM);
            }

            if (totalCount > 0) {
                map.put("offset", page.getOffset());
                map.put("pageSize", page.getRowNum());
                List<CourseBaseInfoEntity> list = coursesBaseInfoMapper.getCoursesBaseInfoPageList(map);
                CertAuthority certAuthority = new CertAuthority();
                for (CourseBaseInfoEntity cEntity : list) {

                    Integer authorityId = cEntity.getAuthorityId();
                    certAuthority = certAuthorityDao.selectByPrimaryKey(authorityId);
                    cEntity.setAuthorityName(certAuthority.getAuthorityName());

                    String status = cEntity.getStatus();
                    map.put("value", Constants.COURSE_SHELEVES);
                    map.put("status", status);
                    String statusName = cfgParamMapper.getTextByValue(map);
                    cEntity.setStatusName(statusName);

                    String orgName = ParamsUtils.getText(25, 0, cEntity.getAuthenticateGrade());
                    cEntity.setOrgName(orgName);
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

    /***
     * 总控--分校管理--课程管理--新增
     */
    @Override
    public int addCourseBaseInfo(CourseBaseInfoForm courseBaseInfoForm) {
        CourseBaseInfo cInfo = new CourseBaseInfo();
        CourseInstitution courseInstitution = new CourseInstitution();
        SysUsersEntity sysUsersEntity = UserUtils.getUser();

        cInfo.setCourseName(courseBaseInfoForm.getCourseName());
        cInfo.setAuthenticateGrade(courseBaseInfoForm.getAuthenticateGrade());
        cInfo.setTotalHours(courseBaseInfoForm.getTotalHours());
        cInfo.setExamType(courseBaseInfoForm.getExamType());
        cInfo.setAuthorityId(courseBaseInfoForm.getAuthorityId());
        cInfo.setRemarks(courseBaseInfoForm.getRemark());
        cInfo.setStatus(courseBaseInfoForm.getStatus());
        cInfo.setCertName(courseBaseInfoForm.getCertName());
        cInfo.setAddAccount(UserUtils.getUser().getLoginAccount());
        cInfo.setAddTime(new Date());

        int res = coursesBaseInfoMapper.addCourseBaseInfoByEntityForm(cInfo);
        if (res <= 0) {
            throw new ServiceException("插入总控表课程信息失败");
        }
        int courseId = cInfo.getCourseId();

        List<InstitutionInfo> list = institutionInfoMapper.getInstitutionId();
        if (list == null || list.size() <= 0) {
            throw new ServiceException("机构表中不存在信息");
        }
        for (InstitutionInfo info : list) {
            courseInstitution.setInstitutionId(info.getId());
            courseInstitution.setCourseId(courseId);
            courseInstitution.setAddAccount(sysUsersEntity.getLoginAccount());
            courseInstitution.setAddTime(new Date());

            int result = courseInstitutionMapper.insertInfo(courseInstitution);
            if (result <= 0) {
                throw new ServiceException("插入CourseInstitution表信息失败");
            }
        }
        return 1;
    }

    /**
     * 获取单个课程信息
     */
    @Override
    public CourseBaseInfoEntity getCourseBaseInfoById(Integer courseId) {
        CourseBaseInfoEntity cEntity = coursesBaseInfoMapper.getCourseBaseInfoById(courseId);
        if (cEntity == null) {
            return null;
        }

        String orgName = ParamsUtils.getText(25, 0, cEntity.getAuthenticateGrade());
        cEntity.setOrgName(orgName);

        CertAuthority certAuthority = certAuthorityDao.selectByPrimaryKey(cEntity.getAuthorityId());
        cEntity.setAuthorityName(certAuthority.getAuthorityName());

        return cEntity;
    }


    /***
     * 总控--分校管理--课程管理--修改
     */
    @Override
    public int updateCourseBaseInfo(CourseBaseInfoForm courseBaseInfoForm) {

        Map<Object, Object> map = new HashMap<>();
        CourseBaseInfoEntity cEntity = new CourseBaseInfoEntity();
        cEntity.setCourseId(courseBaseInfoForm.getCourseId());
        cEntity.setTotalHours(courseBaseInfoForm.getTotalHours());
        cEntity.setExamType(courseBaseInfoForm.getExamType());
        cEntity.setRemarks(courseBaseInfoForm.getRemark());
        cEntity.setStatus(courseBaseInfoForm.getStatus());
        cEntity.setModifyAccount(UserUtils.getUser().getLoginAccount());
        cEntity.setModifyTime(new Date());
        int result = coursesBaseInfoMapper.updaeCourseBaseInfo(cEntity);
        if (result < 0) {
            return -1;
        }
        map.put("courseBaseInfoId", courseBaseInfoForm.getCourseId());
        map.put("status", "02");
        if ("02".equals(courseBaseInfoForm.getStatus())) {
            learnTypesDao.updateCourseStatus(map);
            //1. find learnTypes list
            List<LearnTypes> list = learnTypesDao.getLeanTypeByCourseBaseId(courseBaseInfoForm.getCourseId());
            //2. batch update coursePackages
            for (LearnTypes lt : list) {
                if (lt.getId() != null) {
                    coursePackageMapper.batchUpdateStatus(lt.getId()+"");
                }
            }
        }
        return 1;
    }

}
