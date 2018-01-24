package com.jiacer.modules.business.utils;

import java.util.List;
import com.google.common.collect.Lists;
import com.jiacer.modules.common.utils.CacheUtils;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.CourseInfoMapper;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.entity.CourseInfoEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.system.config.DBStatus;

/** 
* @ClassName: CoursesUtils 
* @Description: 课程utils 缓存
* @author 贺章鹏
* @date 2016年11月1日 上午11:58:02 
*  
*/
public class CoursesUtils {
	
	public static final String CACHE_COURSES_LIST = "coursesList";
	
	private static CourseInfoMapper coursesDao = SpringContextHolder.getBean(CourseInfoMapper.class);
	
	/**
	 * 获取课程所有
	 */
	public static List<CourseInfoEntity> init(String status){
		
		List<CourseInfoEntity> result=Lists.newArrayList();
		CourseInfoEntity entity=new CourseInfoEntity();
		if(!"all".equalsIgnoreCase(status)){
			entity.setStatus(DBStatus.CourseStatus.NOMAL);
		}
		try {
			result=coursesDao.findAllList(entity);
		} catch (Exception e) {
			Log.error("获取课程数据失败");
		}
		return result;
	}
	
	
	public static String getName(Integer id){
		List<CourseInfoEntity> list=init(null);
		for(CourseInfoEntity courseInfoEntity:list){
			if(id==courseInfoEntity.getCourseId()){
				return courseInfoEntity.getCourseName();
			}
		}
		return "";
	}
	
	/**
	 * 清除缓存
	 */
	public static void clear(){
		if (CacheUtils.getCacheManager().getCache(CACHE_COURSES_LIST)!= null) {
			CacheUtils.getCacheManager().getCache(CACHE_COURSES_LIST).removeAll();
		}else {
			init(null);
		}
		//CacheUtils.getCacheManager().getCache(CACHE_COURSES_LIST).removeAll();
	}
	
	/**
	 * 移除指定缓存
	 * @param key
	 */
	public static void remove(String key){
		CacheUtils.remove(CACHE_COURSES_LIST,key);
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<CourseInfoEntity> getCache(String key){
		if(CacheUtils.get(CACHE_COURSES_LIST, key)!=null){
			return (List<CourseInfoEntity>)CacheUtils.get(CACHE_COURSES_LIST, key);
		}
		return null;
	}
	
	/**
	 * 添加缓存
	 * @param key
	 */
	public static void putCache(String key,List<CourseInfoEntity> value){
		CacheUtils.put(CACHE_COURSES_LIST, key, value);
	}
	
	/**
	 * 刷新缓存
	 */
	public static void flush(){
		clear();
		init(null);
	}
	
	public static CourseInfoEntity getObject(Integer id){
		List<CourseInfoEntity> list=init(null);
		for(CourseInfoEntity courseInfoEntity:list){
			if(id==courseInfoEntity.getCourseId()){
				return courseInfoEntity;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getSchoolCourses(String values){
		String[] ss=StringUtils.split(values,",");
		StringBuilder result = new StringBuilder();
		for(int i=0;i<ss.length;i++){
			String value=CoursesUtils.getName(Integer.parseInt(ss[i]));
			if(!StringUtils.isEmpty(value)){
				result.append(value).append(",");
			}
		}
		if(result!=null){
			return result.substring(0, result.length()-1);
		}
		return result.toString();
	}
	
	public static List<CourseInfoEntity> getCourses(String values, String status){
		List<CourseInfoEntity> newList=Lists.newArrayList();
		List<CourseInfoEntity> list=init(null);
		String[] ss=StringUtils.split(values,",");
		int i=0,j=ss.length;
		for(CourseInfoEntity courseInfoEntity:list){
			for(i=0;i<ss.length;i++){
				if(courseInfoEntity.getCourseId()==Integer.parseInt(ss[i])){
					newList.add(courseInfoEntity);
					j--;
				}
			}
			if(j==0){
				break;
			}
		}
		return newList;
	}
}
