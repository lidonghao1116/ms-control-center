package com.jiacer.modules.business.utils;

import java.util.ArrayList;
import java.util.List;

import com.jiacer.modules.business.bean.vo.CourseBaseInfoVo;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.mybatis.dao.CoursesBaseInfoMapper;
import com.jiacer.modules.mybatis.entity.CourseBaseInfoEntity;

/**
 * 
 * @ClassName:CoursesBaseInfoUtils.java
 * @Description:获取总控基础课程信息
 * @Author:ticahmfock
 * @Date:Sep 26, 2017 10:54:06 AM
 */
public class CoursesBaseInfoUtils {

	private static CoursesBaseInfoMapper coursesBaseInfoMapper = SpringContextHolder.getBean(CoursesBaseInfoMapper.class);

	/**
	 * 
	 * @MethodName:getCoursesBaseInfo
	 * @Type:CoursesBaseInfoUtils
	 * @Description:获取总控基础课程信息
	 * @Return:List<CourseBaseInfoVo>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 26, 2017 10:59:40 AM
	 */
	public static List<CourseBaseInfoVo> getCoursesBaseInfo() {

		List<CourseBaseInfoVo> cList = new ArrayList<CourseBaseInfoVo>();
		CourseBaseInfoVo cVo =null;
		List<CourseBaseInfoEntity> cBaseInfos = coursesBaseInfoMapper.getCourseInfo();
		for (CourseBaseInfoEntity  cEntity : cBaseInfos) {
			cVo=new CourseBaseInfoVo();
			cVo.setCourseId(cEntity.getCourseId());
			cVo.setCourseName(cEntity.getCourseName());
			cList.add(cVo);
		}
		return cList;
		
	}

	
	
}
