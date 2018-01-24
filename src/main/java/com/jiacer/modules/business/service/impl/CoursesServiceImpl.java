package com.jiacer.modules.business.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiacer.modules.business.bean.operation.LearnTypesOpt;
import com.jiacer.modules.business.service.CoursesService;
import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.CourseInfoMapper;
import com.jiacer.modules.mybatis.dao.TemplateInfoMapper;
import com.jiacer.modules.mybatis.entity.CourseInfoEntity;
import com.jiacer.modules.mybatis.entity.TemplateInfoEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: CoursesServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年10月19日 下午4:19:10 
*  
*/
@Service
public class CoursesServiceImpl extends BaseService implements CoursesService {
	
	@Autowired
	CourseInfoMapper courseInfoDao;
	@Autowired
	TemplateInfoMapper templateInfoDao;
	@Override
	public CourseInfoEntity getCoursesById(Integer courseId) {
		try {
			CourseInfoEntity entity =  courseInfoDao.selectByCourseId(courseId);
			if (entity == null) {
				return new CourseInfoEntity();
			}
			TemplateInfoEntity templateInfoTf = templateInfoDao.selectTfByCourseId(entity.getCourseId());
			if (templateInfoTf != null) {
				entity.setTemplateInfoTf(templateInfoTf);
			}else {
				TemplateInfoEntity templateInfoEntity = new TemplateInfoEntity();
				templateInfoEntity.setSubjectCount("");
				entity.setTemplateInfoTf(templateInfoEntity);
			}
			TemplateInfoEntity templateInfoSc = templateInfoDao.selectScByCourseId(entity.getCourseId());
			if (templateInfoSc != null) {
				entity.setTemplateInfoSc(templateInfoSc);
			}else {
				TemplateInfoEntity templateInfoEntity = new TemplateInfoEntity();
				templateInfoEntity.setSubjectCount("");
				entity.setTemplateInfoSc(templateInfoEntity);
			}
			TemplateInfoEntity templateInfoMc = templateInfoDao.selectMcByCourseId(entity.getCourseId());
			if (templateInfoMc != null) {
				entity.setTemplateInfoMc(templateInfoMc);
			}else {
				TemplateInfoEntity templateInfoEntity = new TemplateInfoEntity();
				templateInfoEntity.setSubjectCount("");
				entity.setTemplateInfoMc(templateInfoEntity);
			}
			return entity;
		} catch (Exception e) {
			Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
		}
		return null;
	}
	//列表查询
	@Override
	public Page<CourseInfoEntity> getCoursesPage(CourseInfoEntity courseInfoEntity, int pageNumber, int pageSize) {
		try {
            //获取总条数
			Map<Object, Object>  map=LearnTypesOpt.buildMap(courseInfoEntity);
            Integer totalCount=courseInfoDao.count(map);
            //分页实体
            Page<CourseInfoEntity> page=new Page<CourseInfoEntity>();
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
            	List<CourseInfoEntity> list=courseInfoDao.getPageList(map);
            	
            	TemplateInfoEntity templateInfoTf = null;
            	TemplateInfoEntity templateInfoSc = null;
            	TemplateInfoEntity templateInfoMc = null;
            	
            	for(CourseInfoEntity entity : list){
            		templateInfoTf = new TemplateInfoEntity();
            		templateInfoTf = templateInfoDao.selectTfByCourseId(entity.getCourseId());
            		if (templateInfoTf != null) {
						entity.setTemplateInfoTf(templateInfoTf);
					}else {
						TemplateInfoEntity templateInfoEntity = new TemplateInfoEntity();
						templateInfoEntity.setSubjectCount("");
						entity.setTemplateInfoTf(templateInfoEntity);
					}
            		
            		templateInfoSc = new TemplateInfoEntity();
            		templateInfoSc = templateInfoDao.selectScByCourseId(entity.getCourseId());
            		if (templateInfoSc != null) {
						entity.setTemplateInfoSc(templateInfoSc);
					}else {
						TemplateInfoEntity templateInfoEntity = new TemplateInfoEntity();
						templateInfoEntity.setSubjectCount("");
						entity.setTemplateInfoSc(templateInfoEntity);
					}
            		
            		templateInfoMc = new TemplateInfoEntity();
            		templateInfoMc = templateInfoDao.selectMcByCourseId(entity.getCourseId());
            		if (templateInfoMc != null) {
						entity.setTemplateInfoMc(templateInfoMc);
					}else {
						TemplateInfoEntity templateInfoEntity = new TemplateInfoEntity();
						templateInfoEntity.setSubjectCount("");
						entity.setTemplateInfoMc(templateInfoEntity);
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
	public void addCourses(CourseInfoEntity courseInfoEntity) throws Exception {
	    courseInfoDao.insert(LearnTypesOpt.buildAdd(courseInfoEntity));
	    TemplateInfoEntity entity = courseInfoEntity.getTemplateInfo();
	    if (entity.getTf() != null && !StringUtils.isEmpty(entity.getSubjectCountTF())) {
			entity.setSubjectType("01");//题目类型判断题
			entity.setSubjectCount(entity.getSubjectCountTF());//判断题总数
			entity.setSubjectScore(entity.getTf());//判断题分数
			entity.setIsUsable(DBStatus.IsUsable.TRUE);
			entity.setCourseId(courseInfoEntity.getCourseId());
			entity.setAddTime(new Date());
			entity.setAddAccount(UserUtils.getAccount());
			templateInfoDao.insert(entity);
		}
		if (entity.getSc() != null && !StringUtils.isEmpty(entity.getSubjectCountSC())) {
			entity.setSubjectType("02");//题目类型单选题
			entity.setSubjectCount(entity.getSubjectCountSC());//单选题总数
			entity.setSubjectScore(entity.getSc());//单选题分数
			entity.setIsUsable(DBStatus.IsUsable.TRUE);
			entity.setCourseId(courseInfoEntity.getCourseId());
			entity.setAddTime(new Date());
			entity.setAddAccount(UserUtils.getAccount());
			templateInfoDao.insert(entity);
		}
		if (entity.getMc() != null && !StringUtils.isEmpty(entity.getSubjectCountMC())) {
			entity.setSubjectType("03");//题目类型多选题
			entity.setSubjectCount(entity.getSubjectCountMC());//多选题总数
			entity.setSubjectScore(entity.getMc());//多选题分数
			entity.setIsUsable(DBStatus.IsUsable.TRUE);
			entity.setCourseId(courseInfoEntity.getCourseId());
			entity.setAddTime(new Date());
			entity.setAddAccount(UserUtils.getAccount());
			templateInfoDao.insert(entity);
		}
		
		CoursesUtils.flush();
	}

	@Override
	public void modifyCourses(CourseInfoEntity courseInfoEntity) throws Exception {
		
		CourseInfoEntity bean=this.getCoursesById(courseInfoEntity.getCourseId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}else{
			courseInfoDao.updateByPrimaryKeySe(LearnTypesOpt.buildUpdate(bean, courseInfoEntity));
			TemplateInfoEntity entity = courseInfoEntity.getTemplateInfo();
			 if (entity.getTf() != null && !StringUtils.isEmpty(entity.getSubjectCountTF())) {
				 TemplateInfoEntity templateInfoTf = new TemplateInfoEntity();
         			templateInfoTf = templateInfoDao.selectTfByCourseId(courseInfoEntity.getCourseId());
         			if (templateInfoTf != null) {
         				entity.setSubjectCount(entity.getSubjectCountTF());//判断题总数
    					entity.setSubjectScore(entity.getTf());//判断题分数
    					entity.setCourseId(courseInfoEntity.getCourseId());
    					entity.setSubjectType("01");
    					entity.setModifyTime(new Date());
    					entity.setModifyAccount(UserUtils.getAccount());
    					templateInfoDao.updateByPrimaryKeySe(entity);
					}else {
						entity.setSubjectType("01");//题目类型判断题
						entity.setSubjectCount(entity.getSubjectCountTF());//判断题总数
						entity.setSubjectScore(entity.getTf());//判断题分数
						entity.setIsUsable(DBStatus.IsUsable.TRUE);
						entity.setCourseId(courseInfoEntity.getCourseId());
						entity.setAddTime(new Date());
						entity.setAddAccount(UserUtils.getAccount());
						templateInfoDao.insert(entity);
					}
				
				}
				if (entity.getSc() != null && !StringUtils.isEmpty(entity.getSubjectCountSC())) {
					TemplateInfoEntity templateInfoSc = new TemplateInfoEntity();
            		templateInfoSc = templateInfoDao.selectScByCourseId(courseInfoEntity.getCourseId());
            		if (templateInfoSc != null ) {
            			entity.setSubjectType("02");//题目类型单选题
    					entity.setSubjectCount(entity.getSubjectCountSC());//单选题总数
    					entity.setSubjectScore(entity.getSc());//单选题分数
    					entity.setIsUsable(DBStatus.IsUsable.TRUE);
    					entity.setCourseId(courseInfoEntity.getCourseId());
    					entity.setModifyTime(new Date());
    					entity.setModifyAccount(UserUtils.getAccount());
    					templateInfoDao.updateByPrimaryKeySe(entity);
					}else {
						entity.setSubjectType("02");//题目类型单选题
						entity.setSubjectCount(entity.getSubjectCountSC());//单选题总数
						entity.setSubjectScore(entity.getSc());//单选题分数
						entity.setIsUsable(DBStatus.IsUsable.TRUE);
						entity.setCourseId(courseInfoEntity.getCourseId());
						entity.setAddTime(new Date());
						entity.setAddAccount(UserUtils.getAccount());
						templateInfoDao.insert(entity);
					}
					
				}
				if (entity.getMc() != null && !StringUtils.isEmpty(entity.getSubjectCountMC())) {
					TemplateInfoEntity	templateInfoMc = new TemplateInfoEntity();
            		templateInfoMc = templateInfoDao.selectMcByCourseId(courseInfoEntity.getCourseId());
            		if (templateInfoMc != null) {
            			entity.setSubjectType("03");//题目类型多选题
    					entity.setSubjectCount(entity.getSubjectCountMC());//多选题总数
    					entity.setSubjectScore(entity.getMc());//多选题分数
    					entity.setIsUsable(DBStatus.IsUsable.TRUE);
    					entity.setCourseId(courseInfoEntity.getCourseId());
    					entity.setModifyTime(new Date());
    					entity.setModifyAccount(UserUtils.getAccount());
    					templateInfoDao.updateByPrimaryKeySe(entity);
					}else {
						entity.setSubjectType("03");//题目类型多选题
						entity.setSubjectCount(entity.getSubjectCountMC());//多选题总数
						entity.setSubjectScore(entity.getMc());//多选题分数
						entity.setIsUsable(DBStatus.IsUsable.TRUE);
						entity.setCourseId(courseInfoEntity.getCourseId());
						entity.setAddTime(new Date());
						entity.setAddAccount(UserUtils.getAccount());
						templateInfoDao.insert(entity);
					}
				}
		}
		CoursesUtils.flush();
	}
	
	

}
