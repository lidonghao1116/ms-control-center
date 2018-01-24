package com.jiacer.modules.business.validate;

import com.jiacer.modules.business.bean.HandleStatusType;
import com.jiacer.modules.business.utils.ExamsUtils;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;

/** 
* @ClassName: ApplyOrdersValidate 
* @Description: 学员申请订单管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:13:49 
*  
*/
public class ApplyOrdersValidate {

	public static JsonResult modifyValidate(ApplyOrdersEntity applyOrdersEntity,String type) {
		if(HandleStatusType.SUCCESS_APPLY.getValue().equals(type)){//已报名学员修改
			if(applyOrdersEntity.getClassNumber()==null){
				return new JsonResult(false,"请选择班级编号",null);
			}
			if(StringUtils.isEmpty(applyOrdersEntity.getIsHasPf())){
				return new JsonResult(false,"请选择是否交金",null);
			}
			
			if(StringUtils.isEmpty(applyOrdersEntity.getIsStaging())){
				return new JsonResult(false,"请选择是否分期",null);
			}
			
		}else if(HandleStatusType.SPECIAL.getValue().equals(type)){//特殊处理--之前没有已通过的订单
			UserExtendInfoEntity param=applyOrdersEntity.getUserExtendInfo();
			if(param==null){
				return new JsonResult(false,"参数错误",null);
			}
			if(StringUtils.isEmpty(param.getUserName())){
				return new JsonResult(false,"请填写用户姓名",null);
			}
			
			if(StringUtils.isEmpty(param.getCertNo())){
				return new JsonResult(false,"请填写身份证",null);
			}
			
			if(!BaseValidate.isIdcard(param.getCertNo())){
				return new JsonResult(false,"请填写正确的身份证",null);
			}
			
			if(StringUtils.isEmpty(param.getEducation())){
				return new JsonResult(false,"请选择学历",null);
			}
			
			if(StringUtils.isEmpty(param.getNation())){
				return new JsonResult(false,"请选择民族",null);
			}
			
			if(StringUtils.isEmpty(param.getBirthplace())){
				return new JsonResult(false,"请选择籍贯",null);
			}
			
			if(StringUtils.isEmpty(param.getAddress())){
				return new JsonResult(false,"请填写户籍地址",null);
			}
			
			if(StringUtils.isEmpty(param.getSourceType())){
				return new JsonResult(false,"请选择来源",null);
			}
			
			if(StringUtils.isEmpty(param.getSourceTypeSec())){
				return new JsonResult(false,"请选择来源",null);
			}
			
			if(StringUtils.isEmpty(param.getSourceValue())){
				return new JsonResult(false,"请填写或选择来源",null);
			}
			
			if(StringUtils.isEmpty(applyOrdersEntity.getClassTime())){
				return new JsonResult(false,"请选择上课时间",null);
			}
			
			if(applyOrdersEntity.getClassNumber()==null){
				return new JsonResult(false,"请选择班级编号",null);
			}
			
			if(StringUtils.isEmpty(applyOrdersEntity.getIsExam())){
				return new JsonResult(false,"请选择是否考试",null);
			}
			
			if(applyOrdersEntity.getSchoolFee()==null){
				return new JsonResult(false,"请填写学费",null);
			}
			
			if(StringUtils.isEmpty(applyOrdersEntity.getIsStaging())){
				return new JsonResult(false,"请选择是否分期",null);
			}
			
		}else if(HandleStatusType.REP_APPLY.getValue().equals(type)){//补考订单
			if(applyOrdersEntity.getClassNumber()==null){
				return new JsonResult(false,"请选择班级编号",null);
			}
			if(applyOrdersEntity.getMakeupFee()==null){
				return new JsonResult(false,"请填写补考金额",null);
			}
		}else if(HandleStatusType.PEND_APPLY.getValue().equals(type)){
			if(StringUtils.isEmpty(applyOrdersEntity.getClassTime())){
				return new JsonResult(false,"请选择上课时间",null);
			}
			
			if(applyOrdersEntity.getClassNumber()==null){
				return new JsonResult(false,"请选择班级编号",null);
			}
			
			if(StringUtils.isEmpty(applyOrdersEntity.getIsExam())){
				return new JsonResult(false,"请选择是否考试",null);
			}
			
			if(applyOrdersEntity.getSchoolFee()==null){
				return new JsonResult(false,"请填写学费",null);
			}
			
			if(StringUtils.isEmpty(applyOrdersEntity.getIsStaging())){
				return new JsonResult(false,"请选择是否分期",null);
			}
		}
		return new JsonResult(true,null,null);
	}

	public static JsonResult addValidate(ApplyOrdersEntity applyOrdersEntity) {
		UserExtendInfoEntity param=applyOrdersEntity.getUserExtendInfo();
		UserBaseInfoEntity userParam=applyOrdersEntity.getUserBaseInfo();
		if(param==null || userParam==null){
			return new JsonResult(false,"参数错误",null);
		}
		
		if(StringUtils.isEmpty(userParam.getMobile())){
			return new JsonResult(false,"请填写手机号码",null);
		}
		
		if(!BaseValidate.isMobile(userParam.getMobile())){
			return new JsonResult(false,"请填写正确的手机号码",null);
		}
		
		if(StringUtils.isEmpty(param.getUserName())){
			return new JsonResult(false,"请填写用户姓名",null);
		}
		
		if(StringUtils.isEmpty(param.getCertNo())){
			return new JsonResult(false,"请填写身份证",null);
		}
		
		if(!BaseValidate.isIdcard(param.getCertNo())){
			return new JsonResult(false,"请填写正确的身份证",null);
		}
		
		if(StringUtils.isEmpty(param.getEducation())){
			return new JsonResult(false,"请选择学历",null);
		}
		
		if(StringUtils.isEmpty(param.getNation())){
			return new JsonResult(false,"请选择民族",null);
		}
		
		if(StringUtils.isEmpty(param.getBirthplace())){
			return new JsonResult(false,"请选择籍贯",null);
		}
		
		if(StringUtils.isEmpty(param.getAddress())){
			return new JsonResult(false,"请填写户籍地址",null);
		}
		
		if(StringUtils.isEmpty(param.getSourceType())){
			return new JsonResult(false,"请选择来源",null);
		}
		
		if(StringUtils.isEmpty(param.getSourceTypeSec())){
			return new JsonResult(false,"请选择来源",null);
		}
		
		if(StringUtils.isEmpty(param.getSourceValue())){
			return new JsonResult(false,"请填写或选择来源",null);
		}
		
		if(applyOrdersEntity.getPackageId()==null){
			return new JsonResult(false,"请选择产品",null);
		}
		
		if(applyOrdersEntity.getCourseId()==null){
			return new JsonResult(false,"请选择课程",null);
		}
		
		if(StringUtils.isEmpty(applyOrdersEntity.getClassTime())){
			return new JsonResult(false,"请选择上课时间",null);
		}
		
		if(applyOrdersEntity.getClassNumber()==null){
			return new JsonResult(false,"请选择班级编号",null);
		}
		
		if(StringUtils.isEmpty(applyOrdersEntity.getIsExam())){
			return new JsonResult(false,"请选择是否考试",null);
		}
		
		if(applyOrdersEntity.getSchoolFee()==null){
			return new JsonResult(false,"请填写学费",null);
		}
		
		if(StringUtils.isEmpty(applyOrdersEntity.getIsStaging())){
			return new JsonResult(false,"请选择是否分期",null);
		}
		
		ExamClassEntity examClassEntity=ExamsUtils.getClassObjcet(applyOrdersEntity.getClassNumber());
		if(applyOrdersEntity.getCourseId()!=examClassEntity.getCourseId()){
			return new JsonResult(false,"所选班级没有此课程，请检查",null);
		}
		
		return new JsonResult(true,null,null);
	}

}
