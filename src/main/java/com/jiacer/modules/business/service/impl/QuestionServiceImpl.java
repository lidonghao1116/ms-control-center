package com.jiacer.modules.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiacer.modules.business.service.QuestionService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.AnswersMapper;
import com.jiacer.modules.mybatis.dao.QuestionsMapper;
import com.jiacer.modules.mybatis.entity.AnswersEntity;
import com.jiacer.modules.mybatis.entity.QuestionsEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.UserUtils;

@Service
public class QuestionServiceImpl extends BaseService implements QuestionService{

	@Autowired
	QuestionsMapper questionsDao;
	@Autowired
	AnswersMapper answersDao;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getById(Integer id) {
		try {
			
			QuestionsEntity questionsEntity = questionsDao.getById(id);
			
			List list = answersDao.selectByQuestionId(id);
			List list2 = new ArrayList();
			
			list2.add(list);
			list2.add(questionsEntity);
			return list2;
		} catch (Exception e) {
			Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
		}
		return null;
	}
	
	
	@Override
	public Page<QuestionsEntity> getQuestionPage(QuestionsEntity questionsEntity,int pageNumber, int pageSize){
		try {
			 //获取总条数
			Map<Object, Object>  map =new HashMap<Object, Object>();
			map.put("question",questionsEntity.getQuestion());
			map.put("status", questionsEntity.getStatus());
			map.put("questionType", questionsEntity.getQuestionType());
			map.put("courseId", questionsEntity.getCourseId());
            Integer totalCount=questionsDao.count(map);
            //分页实体
            Page<QuestionsEntity> page=new Page<QuestionsEntity>();
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
            	List<QuestionsEntity> list=questionsDao.getPageList(map);
            	
            	/*for(QuestionsEntity entity : list){
            		AnswersEntity answers = new AnswersEntity();
            		answersDao.selectByQuestionId(entity.getId());
            	}*/
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
	public void addquestion(QuestionsEntity questionsEntity)throws Exception{
		questionsEntity.setAddAccount(UserUtils.getAccount());
		questionsEntity.setAddTime(new Date());
		questionsEntity.setIsUsable(DBStatus.IsUsable.TRUE);
		questionsEntity.setTypeId( new Integer(0));
		questionsDao.insert(questionsEntity);
		AnswersEntity parm = questionsEntity.getAnswers();
		//单选题
		if (questionsEntity.getQuestionType().equals("01")) {
			if (!"".equals(parm.getAnswerDesc1())) {
				parm.setAnswerDesc(parm.getAnswerDesc1());
				parm.setQuestionsId(questionsEntity.getId());
				parm.setIsAnswer(parm.getIsAnswer1());
				parm.setIsUsable(DBStatus.IsUsable.TRUE);
				answersDao.insert(parm);
			}
			if (!"".equals(parm.getAnswerDesc2())) {
				parm.setAnswerDesc(parm.getAnswerDesc2());
				parm.setQuestionsId(questionsEntity.getId());
				parm.setIsAnswer(parm.getIsAnswer2());
				parm.setIsUsable(DBStatus.IsUsable.TRUE);
				answersDao.insert(parm);
			}
			if (!"".equals(parm.getAnswerDesc3())) {
				parm.setAnswerDesc(parm.getAnswerDesc3());
				parm.setQuestionsId(questionsEntity.getId());
				parm.setIsAnswer(parm.getIsAnswer3());
				parm.setIsUsable(DBStatus.IsUsable.TRUE);
				answersDao.insert(parm);
			}
			if (!"".equals(parm.getAnswerDesc4())) {
				parm.setAnswerDesc(parm.getAnswerDesc4());
				parm.setQuestionsId(questionsEntity.getId());
				parm.setIsAnswer(parm.getIsAnswer4());
				parm.setIsUsable(DBStatus.IsUsable.TRUE);
				answersDao.insert(parm);
			}
		}
		//判断题
		if (questionsEntity.getQuestionType().equals("02")) {
			if (!"".equals(parm.getAnswerDesc1())) {
				parm.setAnswerDesc(parm.getAnswerDesc1());
				parm.setQuestionsId(questionsEntity.getId());
				parm.setIsAnswer(parm.getIsAnswer1());
				parm.setIsUsable(DBStatus.IsUsable.TRUE);
				answersDao.insert(parm);
			}
			if (!"".equals(parm.getAnswerDesc2())) {
				parm.setAnswerDesc(parm.getAnswerDesc2());
				parm.setQuestionsId(questionsEntity.getId());
				parm.setIsAnswer(parm.getIsAnswer2());
				parm.setIsUsable(DBStatus.IsUsable.TRUE);
				answersDao.insert(parm);
			}
		}
		//多选题
		if (questionsEntity.getQuestionType().equals("03")) {
			if (!"".equals(parm.getAnswerDesc1())) {
				parm.setAnswerDesc(parm.getAnswerDesc1());
				parm.setQuestionsId(questionsEntity.getId());
				parm.setIsAnswer(parm.getIsAnswer1());
				parm.setIsUsable(DBStatus.IsUsable.TRUE);
				answersDao.insert(parm);
			}
			if (!"".equals(parm.getAnswerDesc2())) {
				parm.setAnswerDesc(parm.getAnswerDesc2());
				parm.setQuestionsId(questionsEntity.getId());
				parm.setIsAnswer(parm.getIsAnswer2());
				parm.setIsUsable(DBStatus.IsUsable.TRUE);
				answersDao.insert(parm);
			}
			if (!"".equals(parm.getAnswerDesc3())) {
				parm.setAnswerDesc(parm.getAnswerDesc3());
				parm.setQuestionsId(questionsEntity.getId());
				parm.setIsAnswer(parm.getIsAnswer3());
				parm.setIsUsable(DBStatus.IsUsable.TRUE);
				answersDao.insert(parm);
			}
			if (!"".equals(parm.getAnswerDesc4())) {
				parm.setAnswerDesc(parm.getAnswerDesc4());
				parm.setQuestionsId(questionsEntity.getId());
				parm.setIsAnswer(parm.getIsAnswer4());
				parm.setIsUsable(DBStatus.IsUsable.TRUE);
				answersDao.insert(parm);
			}
			if (!"".equals(parm.getAnswerDesc5())) {
				parm.setAnswerDesc(parm.getAnswerDesc5());
				parm.setQuestionsId(questionsEntity.getId());
				parm.setIsAnswer(parm.getIsAnswer5());
				parm.setIsUsable(DBStatus.IsUsable.TRUE);
				answersDao.insert(parm);
			}
		}
		
	}
	
	@Override
	public void modifyquestion(QuestionsEntity questionsEntity) throws Exception {
		
		QuestionsEntity bean = questionsDao.getById(questionsEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}else{
			bean.setUpdateTime(new Date());
			bean.setUpdateAccount(UserUtils.getAccount());
			bean.setId(questionsEntity.getId());
			bean.setQuestion(questionsEntity.getQuestion());
			bean.setStatus(questionsEntity.getStatus());
			bean.setIsUsable(DBStatus.IsUsable.TRUE);
			questionsDao.update(bean);
			AnswersEntity parm = questionsEntity.getAnswers();
			//单选题
			if (questionsEntity.getQuestionType().equals("01")) {
				if (!"".equals(parm.getAnswerDesc1())) {
					parm.setId(parm.getAnswerId1());
					parm.setAnswerDesc(parm.getAnswerDesc1());
					parm.setQuestionsId(questionsEntity.getId());
					parm.setIsAnswer(parm.getIsAnswer1());
					parm.setIsUsable(DBStatus.IsUsable.TRUE);
					answersDao.update(parm);
				}
				if (!"".equals(parm.getAnswerDesc2())) {
					parm.setId(parm.getAnswerId2());
					parm.setAnswerDesc(parm.getAnswerDesc2());
					parm.setQuestionsId(questionsEntity.getId());
					parm.setIsAnswer(parm.getIsAnswer2());
					parm.setIsUsable(DBStatus.IsUsable.TRUE);
					answersDao.update(parm);
				}
				if (!"".equals(parm.getAnswerDesc3())) {
					parm.setId(parm.getAnswerId3());
					parm.setAnswerDesc(parm.getAnswerDesc3());
					parm.setQuestionsId(questionsEntity.getId());
					parm.setIsAnswer(parm.getIsAnswer3());
					parm.setIsUsable(DBStatus.IsUsable.TRUE);
					answersDao.update(parm);
				}
				if (!"".equals(parm.getAnswerDesc4())) {
					parm.setId(parm.getAnswerId4());
					parm.setAnswerDesc(parm.getAnswerDesc4());
					parm.setQuestionsId(questionsEntity.getId());
					parm.setIsAnswer(parm.getIsAnswer4());
					parm.setIsUsable(DBStatus.IsUsable.TRUE);
					answersDao.update(parm);
				}
			}
			//判断题
			if (questionsEntity.getQuestionType().equals("02")) {
				if (!"".equals(parm.getAnswerDesc1())) {
					parm.setId(parm.getAnswerId1());
					parm.setAnswerDesc(parm.getAnswerDesc1());
					parm.setQuestionsId(questionsEntity.getId());
					parm.setIsAnswer(parm.getIsAnswer1());
					parm.setIsUsable(DBStatus.IsUsable.TRUE);
					answersDao.update(parm);
				}
				if (!"".equals(parm.getAnswerDesc2())) {
					parm.setId(parm.getAnswerId2());
					parm.setAnswerDesc(parm.getAnswerDesc2());
					parm.setQuestionsId(questionsEntity.getId());
					parm.setIsAnswer(parm.getIsAnswer2());
					parm.setIsUsable(DBStatus.IsUsable.TRUE);
					answersDao.update(parm);
				}
			}
			//多选题
			if (questionsEntity.getQuestionType().equals("03")) {
				if (!"".equals(parm.getAnswerDesc1())) {
					parm.setId(parm.getAnswerId1());
					parm.setAnswerDesc(parm.getAnswerDesc1());
					parm.setQuestionsId(questionsEntity.getId());
					parm.setIsAnswer(parm.getIsAnswer1());
					parm.setIsUsable(DBStatus.IsUsable.TRUE);
					answersDao.update(parm);
				}
				if (!"".equals(parm.getAnswerDesc2())) {
					parm.setId(parm.getAnswerId2());
					parm.setAnswerDesc(parm.getAnswerDesc2());
					parm.setQuestionsId(questionsEntity.getId());
					parm.setIsAnswer(parm.getIsAnswer2());
					parm.setIsUsable(DBStatus.IsUsable.TRUE);
					answersDao.update(parm);
				}
				if (!"".equals(parm.getAnswerDesc3())) {
					parm.setId(parm.getAnswerId3());
					parm.setAnswerDesc(parm.getAnswerDesc3());
					parm.setQuestionsId(questionsEntity.getId());
					parm.setIsAnswer(parm.getIsAnswer3());
					parm.setIsUsable(DBStatus.IsUsable.TRUE);
					answersDao.update(parm);
				}
				if (!"".equals(parm.getAnswerDesc4())) {
					parm.setId(parm.getAnswerId4());
					parm.setAnswerDesc(parm.getAnswerDesc4());
					parm.setQuestionsId(questionsEntity.getId());
					parm.setIsAnswer(parm.getIsAnswer4());
					parm.setIsUsable(DBStatus.IsUsable.TRUE);
					answersDao.update(parm);
				}
				if (!"".equals(parm.getAnswerDesc5())) {
					parm.setId(parm.getAnswerId5());
					parm.setAnswerDesc(parm.getAnswerDesc5());
					parm.setQuestionsId(questionsEntity.getId());
					parm.setIsAnswer(parm.getIsAnswer5());
					parm.setIsUsable(DBStatus.IsUsable.TRUE);
					answersDao.update(parm);
				}
			}
		}
	}
	
	
	
	
}
