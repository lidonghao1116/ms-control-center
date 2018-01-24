package com.jiacer.modules.business.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiacer.modules.business.bean.operation.CoursePackageOpt;
import com.jiacer.modules.business.service.CoursePackageService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.CoursePackageMapper;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.system.config.Message;

/** 
* @ClassName: CoursePackageServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年10月19日 下午4:18:58 
*  
*/
@Service
public class CoursePackageServiceImpl extends BaseService implements CoursePackageService {
	
	@Autowired
	CoursePackageMapper coursePackageDao;
	
	@Override
	public CoursePackageEntity getCoursePackageById(Integer id) {
		try {
			return coursePackageDao.getById(id);
		} catch (Exception e) {
			Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
		}
		return null;
	}

	@Override
	public Page<CoursePackageEntity> getCoursePackagePage(CoursePackageEntity coursePackageEntity, int pageNumber,
			int pageSize) {
		try {
            //获取总条数
			Map<Object, Object>  map=CoursePackageOpt.buildMap(coursePackageEntity);
            Integer totalCount=coursePackageDao.count(map);
            //分页实体
            Page<CoursePackageEntity> page=new Page<CoursePackageEntity>();
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
            	List<CoursePackageEntity> list=coursePackageDao.getPageList(map);
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
	public void addCoursePackage(CoursePackageEntity coursePackageEntity) throws Exception {
		coursePackageDao.insert(CoursePackageOpt.buildAdd(coursePackageEntity));
	}

	@Override
	public void modifyCoursePackage(CoursePackageEntity coursePackageEntity) throws Exception {
		CoursePackageEntity bean=this.getCoursePackageById(coursePackageEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}else{
			coursePackageDao.update(CoursePackageOpt.buildUpdate(bean, coursePackageEntity));
		}
	}

	@Override
	public void delCoursePackage(CoursePackageEntity coursePackageEntity) throws Exception {
		CoursePackageEntity bean=this.getCoursePackageById(coursePackageEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}else{
			coursePackageDao.update(CoursePackageOpt.buildDelete(bean));
		}
	}

}
