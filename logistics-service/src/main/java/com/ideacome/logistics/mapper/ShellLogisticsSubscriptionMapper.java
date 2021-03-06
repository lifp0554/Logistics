package com.ideacome.logistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ideacome.logistics.entity.ShellLogisticsSubscription;
import com.ideacome.logistics.entity.ShellLogisticsSubscriptionExample;

public interface ShellLogisticsSubscriptionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics_subscription
     *
     * @mbggenerated Mon Mar 27 14:40:40 CST 2017
     */
    int countByExample(ShellLogisticsSubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics_subscription
     *
     * @mbggenerated Mon Mar 27 14:40:40 CST 2017
     */
    int deleteByExample(ShellLogisticsSubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics_subscription
     *
     * @mbggenerated Mon Mar 27 14:40:40 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics_subscription
     *
     * @mbggenerated Mon Mar 27 14:40:40 CST 2017
     */
    int insert(ShellLogisticsSubscription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics_subscription
     *
     * @mbggenerated Mon Mar 27 14:40:40 CST 2017
     */
    int insertSelective(ShellLogisticsSubscription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics_subscription
     *
     * @mbggenerated Mon Mar 27 14:40:40 CST 2017
     */
    List<ShellLogisticsSubscription> selectByExample(ShellLogisticsSubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics_subscription
     *
     * @mbggenerated Mon Mar 27 14:40:40 CST 2017
     */
    ShellLogisticsSubscription selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics_subscription
     *
     * @mbggenerated Mon Mar 27 14:40:40 CST 2017
     */
    int updateByExampleSelective(@Param("record") ShellLogisticsSubscription record, @Param("example") ShellLogisticsSubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics_subscription
     *
     * @mbggenerated Mon Mar 27 14:40:40 CST 2017
     */
    int updateByExample(@Param("record") ShellLogisticsSubscription record, @Param("example") ShellLogisticsSubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics_subscription
     *
     * @mbggenerated Mon Mar 27 14:40:40 CST 2017
     */
    int updateByPrimaryKeySelective(ShellLogisticsSubscription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shell_logistics_subscription
     *
     * @mbggenerated Mon Mar 27 14:40:40 CST 2017
     */
    int updateByPrimaryKey(ShellLogisticsSubscription record);
    
    /**
     * 按条件查询
     * @param subscription
     * @return
     */
    List<ShellLogisticsSubscription> selectByConditions(ShellLogisticsSubscription subscription);
    
    ShellLogisticsSubscription selectByTrackingNo(String trackingNo);
}