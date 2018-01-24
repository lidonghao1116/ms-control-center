package com.jiacer.modules.business.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Lists;
import com.jiacer.modules.business.bean.vo.CourseBaseInfoVo;
import com.jiacer.modules.business.utils.CertAuthorityInfoUtils;
import com.jiacer.modules.business.utils.CfgParamUtils;
import com.jiacer.modules.business.utils.CoursesBaseInfoUtils;
import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.business.utils.ExamsUtils;
import com.jiacer.modules.business.utils.LearnTypeUtil;
import com.jiacer.modules.business.utils.PartnerUtils;
import com.jiacer.modules.business.utils.ProductsUtils;
import com.jiacer.modules.business.utils.SchoolsUtils;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.entity.AuthorityEntity;
import com.jiacer.modules.mybatis.entity.CourseInfoEntity;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.PartnersEntity;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.mybatis.model.CfgParam;
import com.jiacer.modules.system.config.MappingURL;

/** 
* @ClassName: BusinessParamsController 
* @Description: 系统参数下拉框参数：课程，课程销售、学校
* @author 贺章鹏
* @date 2016年11月4日 下午3:54:46 
*  
*/
@Controller
@RequestMapping(MappingURL.PARAMS_URL)
public class BusinessParamsController extends BaseController{

	/***
	 * 
	 * @MethodName:initCourseInfo
	 * @Type:BusinessParamsController
	 * @Description:总控--分校管理--课程管理--课程名称下拉框
	 * @Return:List<CourseBaseInfoVo>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 26, 2017 10:49:58 AM
	 */
	@RequestMapping(value=MappingURL.COURSES_BASE_INFO_URL,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<CourseBaseInfoVo> initCourseInfo() {
		List<CourseBaseInfoVo> list = new ArrayList<>();
		try {
			list = CoursesBaseInfoUtils.getCoursesBaseInfo();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取总控基础课程信息失败");
		}
		return list;
		
	}
	
	/**
	 * 
	 * @MethodName:initCfgParam
	 * @Type:BusinessParamsController
	 * @Description:总控--分校管理--课程管理--鉴定等级
	 * @Return:List<CfgParam>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 28, 2017 3:45:50 PM
	 */
	@RequestMapping(value=MappingURL.CFG_PARAMS_GRADE_URL,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<CfgParam>  initCfgParamOfGrade(){
		List<CfgParam> list = new ArrayList<>();
		try {
			list=CfgParamUtils.getCfgParamofGrade();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取鉴定等级信息失败");
		}
		return list;
	}
	
	/**
	 * 
	 * @MethodName:initCfgParam
	 * @Type:BusinessParamsController
	 * @Description:总控--分校管理--课程管理--课程状态
	 * @Return:List<CfgParam>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 28, 2017 3:45:50 PM
	 */
	@RequestMapping(value=MappingURL.CFG_PARAMS_STATUS_URL,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<CfgParam>  initCfgParamOfStatus(){
		List<CfgParam> list = new ArrayList<>();
		try {
			list=CfgParamUtils.getCfgParamOfStatus();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取鉴定等级信息失败");
		}
		return list;
	}
	
	
	/**
	 * 
	 * @MethodName:initAuthorityName
	 * @Type:BusinessParamsController
	 * @Description:总控--分校管理--课程管理--发证机构信息
	 * @Return:List<AuthorityEntity>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 27, 2017 5:42:53 PM
	 */
	@RequestMapping(value=MappingURL.AUTHORITY_NAME_URL,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<AuthorityEntity> initAuthorityName(){
		List<AuthorityEntity> list = new ArrayList<>();
		try {
			list=CertAuthorityInfoUtils.getCertAuthorityInfo();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取发证机构信息失败");
		}
		return list;
	}
	
	/**
	 * 
	 * @MethodName:initCfgParamOfExamType
	 * @Type:BusinessParamsController
	 * @Description:总控--分校管理--课程管理--考试形式
	 * @Return:List<CfgParam>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 29, 2017 6:59:25 AM
	 */
	@RequestMapping(value=MappingURL.CFG_PARAMS_EXAM_TYPE_URL,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<CfgParam>  initCfgParamOfExamType(){
		List<CfgParam> list = new ArrayList<>();
		try {
			list=CfgParamUtils.getCfgParamOfExamType();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取鉴定等级信息失败");
		}
		return list;
	}
	
	
	
	@RequestMapping(value = MappingURL.COURSES_URL)
	@ResponseBody
	public List<CourseInfoEntity> initCourses(String values, String status) {
		List<CourseInfoEntity> list = Lists.newArrayList();
		try {
			if(StringUtils.isEmpty(values)){
				list=CoursesUtils.init(status);
			}else{
				list=CoursesUtils.getCourses(values, status);
			}
		} catch (Exception e) {
			Log.error("获取课程列表失败");
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = MappingURL.LEARNTYPE_URL)
	@ResponseBody
	public List<LearnTypesEntity> initLearnType(Integer id,String values,String status) {
		List<LearnTypesEntity> list = Lists.newArrayList();
		try {
			if(id == null){
				list=LearnTypeUtil.init(status);
			}else if(values == null || values.trim().equals("") ||values.trim().equalsIgnoreCase("undefined")){
				list=LearnTypeUtil.getCourses(id,status);
			}else{
				list=LearnTypeUtil.getCourses(id,values,status);
			}
		} catch (Exception e) {
			Log.error("获取学校管理课程列表失败");
			e.printStackTrace();
		}
		return list;
	}
	
	
	@RequestMapping(value = MappingURL.COURSES_PACKAGE_URL)
	@ResponseBody
	public List<CoursePackageEntity> initProducts() {
		List<CoursePackageEntity> list = Lists.newArrayList();
		try {
			list=ProductsUtils.init();
		} catch (Exception e) {
			Log.error("获取销售列表失败");
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = MappingURL.SCHOOLS_URL)
	@ResponseBody
	public List<SchoolsEntity> initSchools() {
		List<SchoolsEntity> list = Lists.newArrayList();
		try {
			list=SchoolsUtils.init();
		} catch (Exception e) {
			Log.error("获取销售列表失败");
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = MappingURL.EXAMS_URL)
	@ResponseBody
	public List<ExamClassEntity> initClass() {
		List<ExamClassEntity> list = Lists.newArrayList();
		try {
			list=ExamsUtils.init();
		} catch (Exception e) {
			Log.error("获取班级列表失败");
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = MappingURL.PARTNERS_URL)
	@ResponseBody
	public List<PartnersEntity> initPartners(String type) {
		List<PartnersEntity> list = Lists.newArrayList();
		try {
			list=PartnerUtils.getPartners(type);
		} catch (Exception e) {
			Log.error("获取合作商列表失败");
			e.printStackTrace();
		}
		return list;
	}
	
	
}
