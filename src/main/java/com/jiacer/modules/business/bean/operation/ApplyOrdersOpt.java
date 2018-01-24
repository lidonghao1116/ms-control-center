package com.jiacer.modules.business.bean.operation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jiacer.modules.business.bean.HandleStatusType;
import com.jiacer.modules.business.bean.IdcardInfoExtractor;
import com.jiacer.modules.business.bean.OptType;
import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.EntryptUtils;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.DBStatus.UserStatus;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: ApplyOrdersOpt 
* @Description: 预约订单对象参数操作
* @author 贺章鹏
* @date 2016年10月24日 下午4:19:47 
*  
*/
public class ApplyOrdersOpt {
	/**
	 * 构建查询参数Map
	 * @param learnTypesEntity
	 * @return
	 */
	public static Map<Object, Object> buildMap(ApplyOrdersEntity applyOrdersEntity){
		Map<Object, Object> map=Maps.newHashMap();
		
			map.put("handleStatus", DBStatus.HandleStatus.SUCCESS);
			
			if(applyOrdersEntity.getStartDate()!=null){
				map.put("startDate", DateUtils.joinTime(applyOrdersEntity.getStartDate(),SysConstants.MIN_TIME));
			}
			if(applyOrdersEntity.getEndDate()!=null){
				map.put("endDate", DateUtils.joinTime(applyOrdersEntity.getEndDate(),SysConstants.MAX_TIME));
			}
			if (applyOrdersEntity.getCourseId()!= null) {
				map.put("courseId", applyOrdersEntity.getCourseId());
			}
			if(!StringUtils.isEmpty(applyOrdersEntity.getUserName())){
				map.put("userName", SqlUtils.like(applyOrdersEntity.getUserName()));
			}
			if(!StringUtils.isEmpty(applyOrdersEntity.getMobile())){
				map.put("moblie", applyOrdersEntity.getMobile());
			}
			if(applyOrdersEntity.getSchoolId()!= null){
				map.put("schoolId", applyOrdersEntity.getSchoolId());
			}
			
		
		return map;
	}
	
	
	
	/**
	 * 构建新增参数
	 * @param obj
	 * @return
	 */
	public static ApplyOrdersEntity buildAdd(ApplyOrdersEntity obj){
		obj.setOrderTime(new Date());
		obj.setOrderType(DBStatus.OrderType.LURU);
		obj.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
		obj.setHandleTime(new Date());
		obj.setHandler(UserUtils.getAccount());
		return obj;
	}
	
	
	public static ApplyOrdersEntity buildModify(ApplyOrdersEntity obj,ApplyOrdersEntity param){
		obj.setModifyTime(param.getModifyTime());
		if(!StringUtils.isEmpty(param.getIsHasPf())){
			 obj.setIsHasPf(param.getIsHasPf());
		}
		if(param.getClassNumber()!=null){
			obj.setClassNumber(param.getClassNumber());
		}
		if(param.getBookFree()!=null){
			obj.setBookFree(param.getBookFree());
		}
		if(!StringUtils.isEmpty(param.getIsStaging())){
			obj.setIsStaging(param.getIsStaging());
		}
		if(param.getFirstPay()!=null){
			obj.setFirstPay(param.getFirstPay());
		}
		if(!StringUtils.isEmpty(param.getIsClear())){
			obj.setIsClear(param.getIsClear());
		}
		return obj;
	}
	
	/**
	 * 后台手动生成客户订单--构建用户信息
	 * @param obj
	 * @return
	 */
	public static UserBaseInfoEntity buildUserInfo(ApplyOrdersEntity obj){
		UserBaseInfoEntity user=obj.getUserBaseInfo();
		UserExtendInfoEntity entity=obj.getUserExtendInfo();
		user.setRegisterTime(new Date());
		user.setSalt(String.format(SysConstants.ENTRY_STRING, user.getMobile()));
		user.setUserStatus(UserStatus.NOMAL);
		user.setIsLocked(Global.NO);
		user.setUserType(DBStatus.UserType.NORMAL);
		user.setLoginPassword(EntryptUtils.entryptUserPassword(entity.getCertNo().substring(entity.getCertNo().length()-6,entity.getCertNo().length()),user.getMobile()));
		return user;
	}
	
	/**
	 * 后台手动生成客户订单--构建用户扩展信息
	 * @param obj
	 * @return
	 */
	public static UserExtendInfoEntity buildUserExtend(ApplyOrdersEntity obj){
		UserExtendInfoEntity entity=obj.getUserExtendInfo();
		entity.setCertType(DBStatus.CertType.SFZ);
	    IdcardInfoExtractor idcardInfo=new IdcardInfoExtractor(entity.getCertNo()); 
		entity.setAge(idcardInfo.getAge());
		entity.setSex(idcardInfo.getGender());
		return entity;
	}



	/**
	 * 构建用户扩展信息
	 * @param applyOrdersEntity
	 * @return
	 */
	public static UserExtendInfoEntity buildSpecialUserExtend(UserExtendInfoEntity obj,UserExtendInfoEntity param) {
		
		if(!StringUtils.isEmpty(param.getUserName())){
			obj.setUserName(param.getUserName());
		}
		
		if(!StringUtils.isEmpty(param.getCertNo())){
			obj.setCertNo(param.getCertNo());
		}
		
		if(!StringUtils.isEmpty(param.getEducation())){
			obj.setEducation(param.getEducation());
		}
		
		if(!StringUtils.isEmpty(param.getNation())){
			obj.setNation(param.getNation());
		}
		
		if(!StringUtils.isEmpty(param.getBirthplace())){
			obj.setBirthplace(param.getBirthplace());
		}
		
		if(!StringUtils.isEmpty(param.getAddress())){
			obj.setAddress(param.getAddress());
		}
		
		if(!StringUtils.isEmpty(param.getSourceType())){
			obj.setSourceType(param.getSourceType());
		}
		
		if(!StringUtils.isEmpty(param.getSourceTypeSec())){
			obj.setSourceTypeSec(param.getSourceTypeSec());
		}
		
		if(!StringUtils.isEmpty(param.getSourceValue())){
			obj.setSourceValue(param.getSourceValue());
		}
		obj.setSourceRemarks(param.getSourceRemarks());
		obj.setContacts(param.getContacts());
		obj.setContactPhone(param.getContactPhone());
		return obj;
	}
	
	public static UserBaseInfoEntity buildModifyPassWord(UserBaseInfoEntity user,UserExtendInfoEntity entity){
		user.setLoginPassword(EntryptUtils.entryptUserPassword(entity.getCertNo().substring(entity.getCertNo().length()-6,entity.getCertNo().length()),user.getMobile()));
		return user;
	}
}
