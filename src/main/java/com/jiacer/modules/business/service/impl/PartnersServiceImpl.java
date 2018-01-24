package com.jiacer.modules.business.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiacer.modules.business.bean.operation.PartnersOpt;
import com.jiacer.modules.business.service.PartnersService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.PartnersMapper;
import com.jiacer.modules.mybatis.entity.PartnersEntity;
import com.jiacer.modules.system.config.Message;

/** 
* @ClassName: PartnersServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年10月19日 下午4:19:49 
*  
*/
@Service
public class PartnersServiceImpl extends BaseService implements PartnersService {
	
	@Autowired
	PartnersMapper partnersDao;
	
	
	@Override
	public PartnersEntity getPartnersById(Integer id) {
		try {
			return partnersDao.getById(id);
		} catch (Exception e) {
			Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
		}
		return null;
	}

	@Override
	public Page<PartnersEntity> getPartnersPage(PartnersEntity partnersEntity, int pageNumber, int pageSize) {
		try {
            //获取总条数
			Map<Object, Object>  map=PartnersOpt.buildMap(partnersEntity);
            Integer totalCount=partnersDao.count(map);
            //分页实体
            Page<PartnersEntity> page=new Page<PartnersEntity>();
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
            	List<PartnersEntity> list=partnersDao.getPageList(map);
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
	public void addPartners(PartnersEntity partnersEntity) throws Exception {
		partnersDao.insert(PartnersOpt.buildAdd(partnersEntity));
	}

	@Override
	public void modifyPartners(PartnersEntity partnersEntity) throws Exception {
		PartnersEntity bean=this.getPartnersById(partnersEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}else{
			partnersDao.update(PartnersOpt.buildUpdate(bean, partnersEntity));
		}
	}

	@Override
	public void delPartner(PartnersEntity partnersEntity) throws Exception {
		PartnersEntity bean=this.getPartnersById(partnersEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}else{
			partnersDao.update(PartnersOpt.buildDelete(bean));
		}
	}

}
