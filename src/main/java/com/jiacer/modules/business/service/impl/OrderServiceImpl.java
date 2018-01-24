package com.jiacer.modules.business.service.impl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.jiacer.modules.business.service.OrderService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.ExcelOutput;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.OrdersMapper;
import com.jiacer.modules.mybatis.dao.UserBaseInfoMapper;
import com.jiacer.modules.mybatis.entity.OrdersEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;

@Service
public class OrderServiceImpl extends BaseService implements OrderService {

	@Autowired
	OrdersMapper ordersDao;
	@Autowired
	UserBaseInfoMapper userBaseInfoDao;
	@Override
	public Page<OrdersEntity> getOrdersPage(OrdersEntity ordersEntity, int pageNumber, int pageSize) {
		try {
            //获取总条数
			Map<Object, Object>  map =new HashMap<Object, Object>();
			if(ordersEntity.getStartDate()!=null){
				map.put("startDate", DateUtils.joinTime(ordersEntity.getStartDate(),SysConstants.MIN_TIME));
			}
			if(ordersEntity.getEndDate()!=null){
				map.put("endDate", DateUtils.joinTime(ordersEntity.getEndDate(),SysConstants.MAX_TIME));
			}
			if(!StringUtils.isEmpty(ordersEntity.getWechatNick())){
				map.put("wechatNick", ordersEntity.getWechatNick());
			}
			if(!StringUtils.isEmpty(ordersEntity.getMobile())){
				map.put("mobile", ordersEntity.getMobile());
			}
			if(!StringUtils.isEmpty(ordersEntity.getOrderNo())){
				map.put("ordersNo", ordersEntity.getOrderNo());
			}
			if(ordersEntity.getCourseId()!= null){
				map.put("courseId", ordersEntity.getCourseId());
			}
			if(!StringUtils.isEmpty(ordersEntity.getIsInvited())){
				map.put("isInvited", ordersEntity.getIsInvited());
			}
            Integer totalCount=ordersDao.count(map);
            //分页实体
            Page<OrdersEntity> page=new Page<OrdersEntity>();
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
            	List<OrdersEntity> list=ordersDao.getPageList(map);
            	
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
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getOrdersInfo(String orderNo) {
		try {
			
		   OrdersEntity orders  = ordersDao.selectByPrimaryKey(orderNo);
		   UserBaseInfoEntity userInfo = userBaseInfoDao.getById(orders.getUserId());	
		   UserBaseInfoEntity inviterUser = userBaseInfoDao.getById(orders.getInviterId());
		   
		   List list = new ArrayList<>();
		   
		   list.add(orders);
		   list.add(userInfo);
		   list.add(inviterUser);
		   return list;
			
		} catch (Exception e) {
			Log.error("获取订单详细失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	public Model dealExport(Model model, OrdersEntity ordersEntity, HttpServletResponse response,
			HttpServletRequest request) {
		
		try {
			Map<Object, Object>  map =new HashMap<Object, Object>();
			if(ordersEntity.getStartDate()!=null){
				map.put("startDate", DateUtils.joinTime(ordersEntity.getStartDate(),SysConstants.MIN_TIME));
			}
			if(ordersEntity.getEndDate()!=null){
				map.put("endDate", DateUtils.joinTime(ordersEntity.getEndDate(),SysConstants.MAX_TIME));
			}
			if(!StringUtils.isEmpty(ordersEntity.getWechatNick())){
				map.put("wechatNick", ordersEntity.getWechatNick());
			}
			if(!StringUtils.isEmpty(ordersEntity.getMobile())){
				map.put("mobile", ordersEntity.getMobile());
			}
			if(!StringUtils.isEmpty(ordersEntity.getOrderNo())){
				map.put("ordersNo", ordersEntity.getOrderNo());
			}
			if(ordersEntity.getCourseId()!= null){
				map.put("courseId", ordersEntity.getCourseId());
			}
			if(!StringUtils.isEmpty(ordersEntity.getIsInvited())){
				map.put("isInvited", ordersEntity.getIsInvited());
			}
			List<OrdersEntity> list= ordersDao.getPageList(map);
			if(list==null || list.size()<1){
				model.addAttribute("message","没有数据可以导出");
			}
			
			String fileName = "订单记录表"+DateUtils.getDate("yyyy-MM-dd")+".xls";
			response.reset();
	        response.addHeader("Content-Disposition", "filename="
	                + new String(fileName.getBytes("gb2312"), "iso8859-1"));
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	        OutputStream out=new BufferedOutputStream(response.getOutputStream());
	        ExcelOutput e = new ExcelOutput(out);
	        int i=0;
	        for(OrdersEntity records:list){
	        
	        e.createRow(i+1);//创建行数
        	e.setCell(1, DateUtils.formatDate(records.getOrderTime(), "yyyy-MM-dd"));
        	e.setCell(2, records.getCourseName());
	    	e.setCell(3, records.getWechatNick());
	    	e.setCell(4, records.getMobile());
	    	e.setCell(5, records.getTradeChannelName());
	    	e.setCell(6, records.getIsInvitedName());
	    	i++;
	        }
	    	e.createRow(0);//表头
	    	e.setCell(1, "购买时间");
	    	e.setCell(2, "购买课程");
	    	e.setCell(3, "用户昵称");
	    	e.setCell(4, "用户手机号");
	    	e.setCell(5, "支付方式");
	    	e.setCell(6, "是否被邀请");
	    	e.export();
		} catch (IOException ex) {
            logger.error("写入excel出错:" + ex);
            model.addAttribute("message","导出失败，程序异常");
        } catch (Exception e) {
			model.addAttribute("message","导出失败，程序异常");
		}
		return model;
	}
}
