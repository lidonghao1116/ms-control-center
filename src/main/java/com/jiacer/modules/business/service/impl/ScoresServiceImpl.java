package com.jiacer.modules.business.service.impl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiacer.modules.mybatis.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jiacer.modules.business.bean.ExamResult;
import com.jiacer.modules.business.bean.operation.ExamClassOpt;
import com.jiacer.modules.business.bean.operation.ScoresOpt;
import com.jiacer.modules.business.service.ScoresService;
import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.ExcelOutput;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.ResultCode.schools;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.ApplyOrdersMapper;
import com.jiacer.modules.mybatis.dao.CertAuthorityMapper;
import com.jiacer.modules.mybatis.dao.ExamClassMapper;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.dao.SchoolsMapper;
import com.jiacer.modules.mybatis.dao.UserAnswersBatchMapper;
import com.jiacer.modules.mybatis.dao.UserBaseInfoMapper;
import com.jiacer.modules.mybatis.dao.UserExtendInfoMapper;
import com.jiacer.modules.mybatis.dao.UserScoresMapper;
import com.jiacer.modules.mybatis.entity.extend.LearnRecordEntity;
import com.jiacer.modules.mybatis.model.CertAuthority;
import com.jiacer.modules.mybatis.model.UserScoresKey;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.DBStatus.DealResult;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.DictionaryUtils;

/** 
* @ClassName: ExamsServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年10月19日 下午4:19:24 
*  
*/
@Service
public class ScoresServiceImpl extends BaseService implements ScoresService {

	@Autowired
	ExamClassMapper examClassDao;
	
	@Autowired
	ApplyOrdersMapper applyOrdersDao;
	
	@Autowired
	UserScoresMapper userScoresDao;
	
	@Autowired
	UserBaseInfoMapper userBaseInfoDao;
	
	@Autowired
	UserExtendInfoMapper userExtendInfoDao;
	
	@Autowired
	UserAnswersBatchMapper userAnswersBatchDao;
	
	@Autowired
	SchoolsMapper schoolsDao;
	
	@Autowired
	LearnTypesMapper learnTypesDao;
	
	@Autowired
	CertAuthorityMapper certAuthorityDao;
	
	@Override
	public Page<Map> getScoresPage(UserScoresEntity userScoresEntity, int pageNumber, int pageSize) {
		try {
            //获取总条数
			Map<Object, Object>  map=new HashMap<>();
			if(userScoresEntity.getStartDate()!=null){
				map.put("startDate", DateUtils.joinTime(userScoresEntity.getStartDate(),SysConstants.MIN_TIME));
			}
			if(userScoresEntity.getEndDate()!=null){
				map.put("endDate", DateUtils.joinTime(userScoresEntity.getEndDate(),SysConstants.MAX_TIME));
			}
			if (userScoresEntity.getCourseId()!= null) {
				map.put("courseId", userScoresEntity.getCourseId());
			}
			if(!StringUtils.isEmpty(userScoresEntity.getUserName())){
				map.put("userName", SqlUtils.like(userScoresEntity.getUserName()));
			}
			if(!StringUtils.isEmpty(userScoresEntity.getMobile())){
				map.put("moblie", userScoresEntity.getMobile());
			}
			if(userScoresEntity.getSchoolId()!= null){
				map.put("schoolId", userScoresEntity.getSchoolId());
			}
			
            Integer totalCount=userScoresDao.count(map);
            //分页实体
            Page<Map> page=new Page<Map>();
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
            	@SuppressWarnings("rawtypes")
				List<Map> list=userScoresDao.getPageList(map);
            	for (Map map2 : list) {
					if (map2.get("authenticateGrade")!=null && !map2.get("authenticateGrade").equals("")) {
						map2.put("authenticateGrade", DictionaryUtils.getAuthenticateGrade((String) map2.get("authenticateGrade")));
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
	public UserScoresEntity getUserScores(Integer schoolId,Integer classId,Integer  userId) {
		UserScoresKey userScoresKey=new UserScoresKey();
		userScoresKey.setUserId(userId);
		userScoresKey.setClassId(classId);
		
		UserScoresEntity bean = new UserScoresEntity();
		
		UserScoresEntity userScores=userScoresDao.getByKey(userScoresKey);
		if(userScores!=null){
			bean=userScores;
		}
		
		UserBaseInfoEntity userInfo=userBaseInfoDao.getById(userId);
		if(userInfo!=null){
			bean.setUserInfo(userInfo);
		}
		
		UserExtendInfoEntity userExtend=userExtendInfoDao.getById(userId);
		if(userExtend!=null){
			bean.setUserExtend(userExtend);
		}
		SchoolsEntity schools = schoolsDao.getById(schoolId);
		if (schools!=null) {
			bean.setSchools(schools);
		}
		ExamClassEntity examClass = examClassDao.getById(classId);
		if (examClass!=null) {
			bean.setExamClass(examClass);
		}
		LearnTypesEntity learnTypes = learnTypesDao.getById(examClass.getCourseId());
		if (learnTypes!=null) {
			bean.setLearnTypes(learnTypes);
		}
		CertAuthority certAuthority = certAuthorityDao.getById(learnTypes.getAuthorityId());
		if (certAuthority!=null) {
			bean.setCertAuthority(certAuthority);
		}

		UserCertEntity cert = userScoresDao.getCertInfoByUserAndClass(userScoresKey);
		bean.setUserCert(cert);

		return bean;
	}

	/*@Override
	public ExamClassEntity getExamClassInfo(Integer id) {
		//ExamClassEntity bean=this.getExamClassById(id);
		if(bean==null){
			return new ExamClassEntity();
		}
		//获取班级下的学员信息
		ApplyOrdersEntity entity=new ApplyOrdersEntity();
		entity.setClassNumber(id);
		entity.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
		List<ApplyOrdersEntity> orderList=applyOrdersDao.findAllList(entity);
		
		if(orderList==null || orderList.size()<1){
			return bean;
		}
		for(ApplyOrdersEntity orders:orderList){
			UserBaseInfoEntity  userBaseInfo=userBaseInfoDao.getById(orders.getUserId());//获取用户基本信息
			UserExtendInfoEntity userExtendInfo=userExtendInfoDao.getById(orders.getUserId());//获取用户基本信息
			
			if(userBaseInfo!=null){
				orders.setUserBaseInfo(userBaseInfo);
			}
			if(userExtendInfo!=null){
				orders.setUserExtendInfo(userExtendInfo);
			}
		}
		bean.setApplyOrders(orderList);
		return bean;
	}

	@Override
	public Model dealExport(Model model, Integer id, HttpServletResponse response,HttpServletRequest request) {
		try {
			ExamClassEntity examClassEntity=this.getExamClassInfo(id);
			if(examClassEntity==null){
				model.addAttribute("message","没有数据可以导出");
				return model;
			}
			
			String fileName = "考试管理表"+DateUtils.getDate("yyyy-MM-dd")+".xls";
			response.reset();
	        response.addHeader("Content-Disposition", "filename="
	                + new String(fileName.getBytes("gb2312"), "iso8859-1"));
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	        OutputStream out=new BufferedOutputStream(response.getOutputStream());
	        String filePath="/WEB-INF/excelTemp/class_temp";
            ExcelOutput e = new ExcelOutput(out,filePath,request);
	        e.getRow(1);//获取
	        e.setCell(1, examClassEntity.getClassName());
	        e.setCell(3, DateUtils.formatDate(examClassEntity.getAddTime(), "yyyy-MM-dd"));
	        e.getRow(2);//获取
	        e.setCell(1, examClassEntity.getCourseName());
	        e.setCell(3, examClassEntity.getSchoolName());
	        e.getRow(3);//获取
	        e.setCell(1, examClassEntity.getClassNumber());
	        e.getRow(6);//获取
	        e.setCell(1, DictionaryUtils.getExamType(examClassEntity.getExamForm()));
	        e.setCell(3, examClassEntity.getExamStatusName());
	        e.getRow(7);//获取
	        e.setCell(1, examClassEntity.getTheoryDate()!=null?DateUtils.formatDate(examClassEntity.getTheoryDate(), "yyyy-MM-dd"):"");
	        e.setCell(3, examClassEntity.getTheoryAddress());
	        e.getRow(8);//获取
	        e.setCell(1, examClassEntity.getOperationDate()!=null?DateUtils.formatDate(examClassEntity.getOperationDate(), "yyyy-MM-dd"):"");
	        e.setCell(3, examClassEntity.getOperationAddress());
	        
	        List<ApplyOrdersEntity> orderList=examClassEntity.getApplyOrders();
	        if(orderList==null || orderList.size()<1){
	        	e.export();
	        }
	        int total=orderList.size();
	        e.getRow(10);//获取
	        e.setCell(0, "学员信息（"+total+"人）");
	        int i=1;
	        e.createRow(10+i);
        	e.setCell(0, "姓名");
        	e.setCell(1, "手机号码");
        	e.setCell(2, "年龄");
        	e.setCell(3, "学历");
        	e.setCell(4, "是否交金");
        	e.setCell(5, "来源");
	        for(ApplyOrdersEntity order:orderList){
	        	e.createRow(11+i);
	        	e.setCell(0, order.getUserExtendInfo()!=null?order.getUserExtendInfo().getUserName():"");
	        	e.setCell(1, order.getUserBaseInfo()!=null?order.getUserBaseInfo().getUserName():"");
	        	e.setCell(2, order.getUserExtendInfo()!=null?order.getUserExtendInfo().getAge():null);
	        	e.setCell(3, order.getUserExtendInfo()!=null?order.getUserExtendInfo().getEducationName():null);
	        	e.setCell(4, order.getIsHasPf().equals("1")?"是":"否");
	        	e.setCell(5, order.getUserExtendInfo()!=null?order.getUserExtendInfo().getSourceValueName():null);
	        	i++;
	        }
	    	e.export();
		} catch (IOException ex) {
            logger.error("写入excel出错:" + ex);
            model.addAttribute("message","导出失败，程序异常");
        } catch (Exception e) {
			model.addAttribute("message","导出失败，程序异常");
		}
		return model;
	}

	@Override
	public Model dealExportScores(Model model, Integer id, HttpServletResponse response, HttpServletRequest request) {
		try {
			ExamClassEntity examClassEntity=this.getExamScores(id);
			if(examClassEntity==null){
				model.addAttribute("message","没有数据可以导出");
				return model;
			}
			
			String fileName = "考试管理表"+DateUtils.getDate("yyyy-MM-dd")+".xls";
			response.reset();
	        response.addHeader("Content-Disposition", "filename="
	                + new String(fileName.getBytes("gb2312"), "iso8859-1"));
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	        OutputStream out=new BufferedOutputStream(response.getOutputStream());
	        String filePath="/WEB-INF/excelTemp/class_temp";
            ExcelOutput e = new ExcelOutput(out,filePath,request);
	        e.getRow(1);//获取
	        e.setCell(1, examClassEntity.getClassName());
	        e.setCell(3, DateUtils.formatDate(examClassEntity.getAddTime(), "yyyy-MM-dd"));
	        e.getRow(2);//获取
	        e.setCell(1, examClassEntity.getCourseName());
	        e.setCell(3, examClassEntity.getSchoolName());
	        e.getRow(3);//获取
	        e.setCell(1, examClassEntity.getClassNumber());
	        e.getRow(6);//获取
	        e.setCell(1, DictionaryUtils.getExamType(examClassEntity.getExamForm()));
	        e.setCell(3, examClassEntity.getExamStatusName());
	        e.getRow(7);//获取
	        e.setCell(1, examClassEntity.getTheoryDate()!=null?DateUtils.formatDate(examClassEntity.getTheoryDate(), "yyyy-MM-dd"):"");
	        e.setCell(3, examClassEntity.getTheoryAddress());
	        e.getRow(8);//获取
	        e.setCell(1, examClassEntity.getOperationDate()!=null?DateUtils.formatDate(examClassEntity.getOperationDate(), "yyyy-MM-dd"):"");
	        e.setCell(3, examClassEntity.getOperationAddress());
	        
	        e.getRow(11);//获取
	        e.setCell(1, examClassEntity.getExamResult()!=null?examClassEntity.getExamResult().getTotleNum():null);
	        e.setCell(3,  examClassEntity.getExamResult()!=null?examClassEntity.getExamResult().getInputNum():null);
	        
	        e.getRow(12);//获取
	        e.setCell(1, examClassEntity.getExamResult()!=null?examClassEntity.getExamResult().getQualifiedNum():null);
	        e.setCell(3,  examClassEntity.getExamResult()!=null && examClassEntity.getExamResult().getQualifiedRate()!=null?examClassEntity.getExamResult().getQualifiedRate().doubleValue():null);
	        
	        List<ApplyOrdersEntity> orderList=examClassEntity.getApplyOrders();
	        if(orderList==null || orderList.size()<1){
	        	e.export();
	        }
	        int total=orderList.size();
	        e.getRow(14);//获取
	        e.setCell(0, "学员成绩（"+total+"人）");
	        int i=1;
	        e.createRow(14+i);
        	e.setCell(0, "姓名");
        	e.setCell(1, "手机号码");
        	e.setCell(2, "理论");
        	e.setCell(3, "实操");
        	e.setCell(4, "考试结果");
        	e.setCell(5, "证书编号");
	        for(ApplyOrdersEntity order:orderList){
	        	e.createRow(15+i);
	        	e.setCell(0, order.getUserExtendInfo()!=null?order.getUserExtendInfo().getUserName():"");
	        	e.setCell(1, order.getUserBaseInfo()!=null?order.getUserBaseInfo().getUserName():"");
	        	e.setCell(2, order.getUserScores()!=null?order.getUserScores().getTheoryScores().doubleValue():null);
	        	e.setCell(3, order.getUserScores()!=null?order.getUserScores().getPoScores().doubleValue():null);
	        	e.setCell(4, DictionaryUtils.getExamResultName(order.getUserScores().getExamResult()));
	        	e.setCell(5, order.getUserScores()!=null?order.getUserScores().getCertificateNo():null);
	        	i++;
	        }
	    	e.export();
		} catch (IOException ex) {
            logger.error("写入excel出错:" + ex);
            model.addAttribute("message","导出失败，程序异常");
        } catch (Exception e) {
			model.addAttribute("message","导出失败，程序异常");
		}
		return model;
	}

	
	@Override
	public List<LearnRecordEntity> getExamLearnRecords(Integer classId) {
		List<LearnRecordEntity> resultList=Lists.newArrayList();
		//获取班级下的学员信息
		ApplyOrdersEntity entity=new ApplyOrdersEntity();
		entity.setClassNumber(classId);
		entity.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
		List<ApplyOrdersEntity> orderList=applyOrdersDao.findAllList(entity);
		if(orderList==null || orderList.size()<1){
			return resultList;
		}
		LearnRecordEntity obj=null;
		Map<Object, Object> map=Maps.newHashMap();
		for(ApplyOrdersEntity orders:orderList){
			 obj=new LearnRecordEntity();
			
			UserBaseInfoEntity userBaseInfoEntity=userBaseInfoDao.getById(orders.getUserId()); 
    		UserExtendInfoEntity extendInfoEntity=userExtendInfoDao.getById(orders.getUserId());
    		if(userBaseInfoEntity!=null){
    			obj.setUserInfo(userBaseInfoEntity);
    		}
    		if(extendInfoEntity!=null){
    			obj.setUserExtend(extendInfoEntity);
    		}
    		map.clear();
    		map.put("classNumber", classId);
    		map.put("isFinished", Global.YES);
    		map.put("userId", orders.getUserId());
    		LearnRecordEntity userRecord=userAnswersBatchDao.getLearnRecord(map);
    		if(userRecord!=null){
    			obj.setScores(userRecord.getScores());
        		obj.setAnswersNum(userRecord.getAnswersNum());
    		}else{
    			obj.setScores(BigDecimal.ZERO);
        		obj.setAnswersNum(0);
    		}
    		
    		resultList.add(obj);
		}
		return resultList;
	}
	
	@Override
	public Model exportExamLearnRecords(Integer classId,Model model, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			List<LearnRecordEntity> resultList=this.getExamLearnRecords(classId);
			if(resultList==null || resultList.size()<1){
				model.addAttribute("message","没有数据可以导出");
			}
			String fileName = "学习记录表"+DateUtils.getDate("yyyy-MM-dd")+".xls";
			response.reset();
	        response.addHeader("Content-Disposition", "filename="
	                + new String(fileName.getBytes("gb2312"), "iso8859-1"));
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	        OutputStream out=new BufferedOutputStream(response.getOutputStream());
	        ExcelOutput e = new ExcelOutput(out);
	        int i=0;
	        for(LearnRecordEntity records:resultList){
	        	e.createRow(i+1);//创建行数o 
        		if(records.getUserExtend()!=null){
        			e.setCell(1, records.getUserExtend().getUserName());
        		}
        		if(records.getUserInfo()!=null){
        			e.setCell(2, records.getUserInfo().getMobile());
        		}
	    		e.setCell(3, records.getAnswersNum());
	    		e.setCell(4, records.getScores().doubleValue());
	    		i++;
	    	}
	    	e.createRow(0);//表头
	    	e.setCell(1, "姓名");
	    	e.setCell(2, "联系方式");
	    	e.setCell(3, "练习次数");
	    	e.setCell(4, "最高成绩");
	    	e.export();
		} catch (IOException ex) {
            logger.error("写入excel出错:" + ex);
            model.addAttribute("message","导出失败，程序异常");
        } catch (Exception e) {
			model.addAttribute("message","导出失败，程序异常");
		}
		return model;
	}*/
}
