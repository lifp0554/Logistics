package com.ideacome.logistics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdang.config.service.zookeeper.ZookeeperConfigGroup;

/**
 * 配置服务
 * @author fangpeng.li
 *
 */
@Service
public class ConfigService{
    @Autowired
    private ZookeeperConfigGroup configGroup;
    
    public static final String KEY = "logistics.key";//快递100分配给公司的授权密匙(Key) 
	
	/**
	 * 快递100订阅请求地址
	 */
	public static final String SUBSCRIPTION_URL = "logistics.subscription.url";
	
    /**
     * 回调地址 
     */
    public static final String CALLBACK_URL = "logistics.callback.url";
    
    /**
     * 快递100实时查询请求地址
     */
    public static final String REALTIME_URL="logistics.realtime.url";
    
    /**
     * 订阅请求格式
     */
    public static final String SCHEMA = "logistics.schema";
    
    /**
     * 快递100分配给公司的编码
     */
    public static final String CUSTOMER ="logistics.customer";
    
    /**
     * 快递100查询快递公司编码
     */
    public static final String COMPANY_CODE_URL = "logistics.company.code.url";
    
    private static Logger LOGGER = LoggerFactory.getLogger(ConfigService.class);
    
    /**
     * 根据key得到value
     * @param key
     * @return
     */
    public String getString(String key){
    	try {
    		String result = configGroup.get(key);
    		return result;
		} catch (Exception Ex) {
			LOGGER.error(Ex.getMessage(), Ex);
			return null;
		}    	
    }
    
}
