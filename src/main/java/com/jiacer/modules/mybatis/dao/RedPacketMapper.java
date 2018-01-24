package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.RedPacketEntity;
import com.jiacer.modules.mybatis.model.RedPacket;
import com.jiacer.modules.mybatis.model.RedPacketExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface RedPacketMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_packet
     *
     * @mbggenerated Fri Jun 02 16:06:04 CST 2017
     */
    int countByExample(RedPacketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_packet
     *
     * @mbggenerated Fri Jun 02 16:06:04 CST 2017
     */
    int deleteByExample(RedPacketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_packet
     *
     * @mbggenerated Fri Jun 02 16:06:04 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_packet
     *
     * @mbggenerated Fri Jun 02 16:06:04 CST 2017
     */
    int insert(RedPacket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_packet
     *
     * @mbggenerated Fri Jun 02 16:06:04 CST 2017
     */
    int insertSelective(RedPacket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_packet
     *
     * @mbggenerated Fri Jun 02 16:06:04 CST 2017
     */
    List<RedPacket> selectByExample(RedPacketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_packet
     *
     * @mbggenerated Fri Jun 02 16:06:04 CST 2017
     */
    RedPacket selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_packet
     *
     * @mbggenerated Fri Jun 02 16:06:04 CST 2017
     */
    int updateByExampleSelective(@Param("record") RedPacket record, @Param("example") RedPacketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_packet
     *
     * @mbggenerated Fri Jun 02 16:06:04 CST 2017
     */
    int updateByExample(@Param("record") RedPacket record, @Param("example") RedPacketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_packet
     *
     * @mbggenerated Fri Jun 02 16:06:04 CST 2017
     */
    int updateByPrimaryKeySelective(RedPacket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_packet
     *
     * @mbggenerated Fri Jun 02 16:06:04 CST 2017
     */
    int updateByPrimaryKey(RedPacket record);
    
    
    /**
	 * @param map
	 * @return
	 */
	Integer count(Map<Object, Object> map);
	/**
	 * @param map
	 * @return
	 */
	List<RedPacketEntity> getRedPacketPageList(Map<Object, Object> map);
	
	/**
	 * 更新红包状态为已清算
	 * @param redPacketEntity
	 * @return
	 */
	
    int updateById(RedPacketEntity redPacketEntity);
}