package com.ideacome.logistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ideacome.logistics.entity.ShellLogistics;
import com.ideacome.logistics.entity.ShellLogisticsExample;
import com.ideacome.logistics.entity.ShellLogisticsWithBLOBs;

public interface ShellLogisticsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    int countByExample(ShellLogisticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    int deleteByExample(ShellLogisticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    int insert(ShellLogisticsWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    int insertSelective(ShellLogisticsWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    List<ShellLogisticsWithBLOBs> selectByExampleWithBLOBs(ShellLogisticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    List<ShellLogistics> selectByExample(ShellLogisticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    ShellLogisticsWithBLOBs selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    int updateByExampleSelective(@Param("record") ShellLogisticsWithBLOBs record, @Param("example") ShellLogisticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    int updateByExampleWithBLOBs(@Param("record") ShellLogisticsWithBLOBs record, @Param("example") ShellLogisticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    int updateByExample(@Param("record") ShellLogistics record, @Param("example") ShellLogisticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    int updateByPrimaryKeySelective(ShellLogisticsWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    int updateByPrimaryKeyWithBLOBs(ShellLogisticsWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    int updateByPrimaryKey(ShellLogistics record);
    
    /**
     * 根据物流单号查询
     * @param trackingNo
     * @return
     */
    ShellLogisticsWithBLOBs selectByTrackingNo(String trackingNo);
    
}