package com.jiacer.modules.business.utils;

import java.util.List;
import com.google.common.collect.Lists;
import com.jiacer.modules.common.utils.CacheUtils;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.system.config.DBStatus;

/** 
* @ClassName: CoursesUtils 
* @Description: 课程utils 缓存
* @author 贺章鹏
* @date 2016年11月1日 上午11:58:02 
*  
*/
public class LearnTypeUtil {
	
	public static final String CACHE_LEARNTYPE_LIST = "learnTypeList";
	
	private static LearnTypesMapper coursesDao = SpringContextHolder.getBean(LearnTypesMapper.class);
	
	/**
	 * 获取课程所有
	 */
	public static List<LearnTypesEntity> init(String status){

		List<LearnTypesEntity> result=Lists.newArrayList();
		LearnTypesEntity entity=new LearnTypesEntity();
		entity.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
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
	public static List<LearnTypesEntity> init(Integer instutionInfoId, String status){

		List<LearnTypesEntity> result=Lists.newArrayList();
		LearnTypesEntity entity=new LearnTypesEntity();
		entity.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
		if(!"all".equalsIgnoreCase(status)){
			entity.setStatus(DBStatus.CourseStatus.NOMAL);
		}
		entity.setSchoolId(instutionInfoId);
		try {
			result=coursesDao.findAllList(entity);
		} catch (Exception e) {
			Log.error("获取课程数据失败");
		}
		return result;
	}
	
	
	public static String getName(Integer id){
		List<LearnTypesEntity> list=init(null);
		for(LearnTypesEntity learnTypesEntity:list){
			if(id==learnTypesEntity.getId()){
				return learnTypesEntity.getTypeName();
			}
		}
		return "";
	}
	
	/**
	 * 清除缓存
	 */
	public static void clear(){
		if (CacheUtils.getCacheManager().getCache(CACHE_LEARNTYPE_LIST)!=null) {
			CacheUtils.getCacheManager().getCache(CACHE_LEARNTYPE_LIST).removeAll();
		}else {
			init(null);
		}
	}
	
	/**
	 * 移除指定缓存
	 * @param key
	 */
	public static void remove(String key){
		CacheUtils.remove(CACHE_LEARNTYPE_LIST,key);
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<LearnTypesEntity> getCache(String key){
		if(CacheUtils.get(CACHE_LEARNTYPE_LIST, key)!=null){
			return (List<LearnTypesEntity>)CacheUtils.get(CACHE_LEARNTYPE_LIST, key);
		}
		return null;
	}
	
	/**
	 * 添加缓存
	 * @param key
	 */
	public static void putCache(String key,List<LearnTypesEntity> value){
		CacheUtils.put(CACHE_LEARNTYPE_LIST, key, value);
	}
	
	/**
	 * 刷新缓存
	 */
	public static void flush(){
		clear();
		init(null);
	}
	
	public static LearnTypesEntity getObject(Integer id){
		List<LearnTypesEntity> list=init(null);
		for(LearnTypesEntity learnTypesEntity:list){
			if(id==learnTypesEntity.getId()){
				return learnTypesEntity;
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
			String value=LearnTypeUtil.getName(Integer.parseInt(ss[i]));
			if(!StringUtils.isEmpty(value)){
				result.append(value).append(",");
			}
		}
		if(result!=null){
			return result.substring(0, result.length()-1);
		}
		return result.toString();
	}

	public static List<LearnTypesEntity> getCourses(Integer id,String values,String status){
		System.out.println(""+id);
		System.out.println(""+values);
		List<LearnTypesEntity> newList=Lists.newArrayList();
		List<LearnTypesEntity> list=init(status);
		String[] ss=StringUtils.split(values,",");
		int i=0,j=ss.length;
		for(LearnTypesEntity learnTypesEntity:list){
			for(i=0;i<ss.length;i++){
				System.out.println(""+learnTypesEntity.getSchoolId());
				if(learnTypesEntity.getId()==Integer.parseInt(ss[i])&&learnTypesEntity.getSchoolId()==id){
					newList.add(learnTypesEntity);
					j--;
				}
			}
			if(j==0){
				break;
			}
		}
		return newList;
	}

	public static List<LearnTypesEntity> getCourses(Integer id,String status){
		List<LearnTypesEntity> list=init(id,status);
		return list;
	}
}
