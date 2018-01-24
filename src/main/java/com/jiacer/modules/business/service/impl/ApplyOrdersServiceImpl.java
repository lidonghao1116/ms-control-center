package com.jiacer.modules.business.service.impl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.*;
import com.jiacer.modules.mybatis.entity.*;
import com.jiacer.modules.mybatis.model.UserScoresKey;
import com.jiacer.modules.system.utils.ParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.google.common.collect.Lists;
import com.jiacer.modules.business.bean.HandleStatusType;
import com.jiacer.modules.business.bean.IdcardInfoExtractor;
import com.jiacer.modules.business.bean.operation.ApplyOrdersOpt;
import com.jiacer.modules.business.service.ApplyOrdersService;
import com.jiacer.modules.business.utils.ExamsUtils;
import com.jiacer.modules.business.utils.UserUtils;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.ExcelOutput;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.Message;

/** 
* @ClassName: ApplyOrdersServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年10月19日 下午4:18:33 
*  
*/
@Service
public class ApplyOrdersServiceImpl extends BaseService implements ApplyOrdersService {
	
	@Autowired
	ApplyOrdersMapper applyOrdersDao;
	
	@Autowired
	UserBaseInfoMapper userBaseInfoDao;
	
	@Autowired
	UserExtendInfoMapper userExtendInfoDao;
	
	@Autowired
	LearnTypesMapper learnTypesDao;

	@Autowired
	UserScoresMapper userScoresMapper;

	@Autowired
	ExamClassMapper examClassMapper;

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	SchoolsMapper schoolsDao;
	@Override
	public Page<ApplyOrdersEntity> getApplyOrdersPage(ApplyOrdersEntity applyOrdersEntity, int pageNumber,
			int pageSize) {
		try {
            //获取总条数
			Map<Object, Object>  map=ApplyOrdersOpt.buildMap(applyOrdersEntity);
            Integer totalCount=applyOrdersDao.count(map);
            //分页实体
            Page<ApplyOrdersEntity> page=new Page<ApplyOrdersEntity>();
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
            	List<ApplyOrdersEntity> list=applyOrdersDao.getPageList(map);
            	UserExtendInfoEntity userExtend=null;
            	UserBaseInfoEntity userBase =null;
            	SchoolsEntity schools = null;
            	LearnTypesEntity learnTypes = null ;
            	for(ApplyOrdersEntity entity:list){
            		userExtend=new UserExtendInfoEntity();
            		userExtend=userExtendInfoDao.getById(entity.getUserId());
            		if(userExtend!=null){
            			entity.setUserExtendInfo(userExtend);
            		}
            		userBase = new UserBaseInfoEntity();
            		userBase = userBaseInfoDao.getById(entity.getUserId());
            		if (userBase != null) {
						entity.setUserBaseInfo(userBase);
					}
            		schools = new SchoolsEntity();
            		schools = schoolsDao.getById(entity.getSchoolId());
            		if (schools != null) {
						entity.setSchools(schools);
					}
            		learnTypes = new LearnTypesEntity();
            		learnTypes = learnTypesDao.getById(entity.getCourseId());
            		if (learnTypes != null) {
						entity.setLearnTypes(learnTypes);
						String orgName = ParamsUtils.getText(25,0,learnTypes.getAuthenticateGrade());
						entity.setOrgName(orgName);
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
	public ApplyOrdersEntity getApplyOrders(Integer id) {
		ApplyOrdersEntity entity=applyOrdersDao.getById(id);
		if(entity==null){
			return new ApplyOrdersEntity();
		}
		UserBaseInfoEntity userInfo=userBaseInfoDao.getById(entity.getUserId());
		UserExtendInfoEntity userExtend=userExtendInfoDao.getById(entity.getUserId());
		SchoolsEntity schools = schoolsDao.getById(entity.getSchoolId());
		LearnTypesEntity learnTypes = learnTypesDao.getById(entity.getCourseId());
		if(userInfo!=null){
			entity.setUserBaseInfo(userInfo);
		}
		if(userExtend!=null){
			entity.setUserExtendInfo(userExtend);
		}
		if (schools!=null) {
			entity.setSchools(schools);
		}
		if (learnTypes!=null) {
			entity.setLearnTypes(learnTypes);

			String orgName = ParamsUtils.getText(25,0,learnTypes.getAuthenticateGrade());
			entity.setOrgName(orgName);
		}
		ExamClassEntity examClass = examClassMapper.getById(entity.getClassNumber());
		if (examClass!=null) {
			entity.setExamClass(examClass);
		}

		if(entity!=null && entity.getStudentId()!=null){
			StudentInfo stu = studentMapper.getById(entity.getStudentId());
			entity.setStudent(stu);
		}

		//查询证书
		UserScoresKey userScoresKey=new UserScoresKey();
		userScoresKey.setUserId(entity.getUserId());
		userScoresKey.setClassId(entity.getClassNumber());
		UserCertEntity cert = userScoresMapper.getCertInfoByUserAndClass(userScoresKey);
		entity.setUserCert(cert);

		return entity;
	}

	
	



	
	@Override
	public Model dealExport(Model model, ApplyOrdersEntity entity,HttpServletResponse response) {
		try {
			Map<Object, Object>  map=ApplyOrdersOpt.buildMap(entity);
			List<ApplyOrdersEntity> list=applyOrdersDao.getPageList(map);
			if(list==null || list.size()<1){
				model.addAttribute("message","没有数据可以导出");
			}
			
			String fileName = "已报名学员表"+DateUtils.getDate("yyyy-MM-dd")+".xls";
			response.reset();
	        response.addHeader("Content-Disposition", "filename="
	                + new String(fileName.getBytes("gb2312"), "iso8859-1"));
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	        OutputStream out=new BufferedOutputStream(response.getOutputStream());
	        ExcelOutput e = new ExcelOutput(out);
	        int i=0;
	        UserExtendInfoEntity userExtendInfo=null; 
	        for(ApplyOrdersEntity orders:list){
	        	userExtendInfo=new UserExtendInfoEntity();
	        	userExtendInfo=userExtendInfoDao.getById(orders.getUserId());//获取用户基本信息
	    		e.createRow(i+1);//创建行数
	    		e.setCell(0, DateUtils.formatDate(orders.getHandleTime(), "yyyy-MM-dd"));//日期
	    		e.setCell(1, userExtendInfo.getUserName());
	    		//e.setCell(2, orders.getProductName());
	    		//e.setCell(3, orders.getCourseName());
	    		e.setCell(4, userExtendInfo.getSourceValueName());
	    		i++;
	    	}
	    	e.createRow(0);//表头
	    	e.setCell(0, "受理时间");
	    	e.setCell(1, "姓名");
	    	e.setCell(2, "产品");
	    	e.setCell(3, "课程");
	    	e.setCell(4, "来源");
	    	e.export();
		} catch (IOException ex) {
            logger.error("写入excel出错:" + ex);
            model.addAttribute("message","导出失败，程序异常");
        } catch (Exception e) {
			model.addAttribute("message","导出失败，程序异常");
		}
		return model;
	}

}
