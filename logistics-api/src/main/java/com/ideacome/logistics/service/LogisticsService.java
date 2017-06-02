package com.ideacome.logistics.service;

import com.ideacome.logistics.entity.ShellLogisticsSubscription;

import java.util.List;
import java.util.Map;

public interface LogisticsService {

	/**
	 * 查询所有订阅未成功的请求
	 * @return
	 */
	public List<Map<String,Object>> selectSubscriptionNotSuccess();
	
	/**
	 * 根据物流单号查询运单物流信息
	 * @param trackingNo
	 * @return
	 */
	public Map<String,Object> selectLogisticsinfo(String trackingNo);
	
	
	/**
	 * 根据物流单号查询运单物流订阅信息
	 * 
	 * @param trackingNo
	 * @return
	 */
	public Map<String, Object> selectLogisticsSubscriptionInfo(String trackingNo);

    /**
     * 订阅物流信息
     * 
     * @param trackingNo
     * @param deliveryCorpCode
     * @return 订阅结果
     */
	public Map<String, Object> subscription(String trackingNo, String deliveryCorpCode);
	
    /**
     * 订阅物流信息
     * 
     * @param logisticsSubscription 参数(orderId,shppingId,trackingNo,deliveryCorpCode)
     * @return 订阅结果
     */
	public Map<String, Object> subscription(ShellLogisticsSubscription logisticsSubscription);
	
	/**
	 * 回调方法
	 * @param param
	 * @param sign
	 * @return
	 */
	public String callbackurl(String param,String sign);
	
	/**
	 * 实时快递查询接口
	 * @param logisticsSubscription
	 * @return
	 */
	public String query(ShellLogisticsSubscription logisticsSubscription);
	
	/**
	 * 根据物流单号查询快递公司代码
	 * @author fangpeng.li
	 * @param trackingNo
	 * @return String
	 */
	public String queryComCode(String trackingNo);
	
}
