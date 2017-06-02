package com.ideacome.logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ideacome.logistics.entity.ShellLogisticsSubscription;
import com.ideacome.logistics.service.ConfigService;
import com.ideacome.logistics.service.LogisticsService;


@Controller
@RequestMapping("/logistics")
public class LogisticsController {
	@Autowired
	LogisticsService logisticsService;
	@Autowired
	ConfigService configService;
	
	/**
	 * 订阅物流信息
	 * @author fangpeng.li
	 * @param deliveryCorpCode
	 * @param trackingNo void
	 */
	@ResponseBody
	@RequestMapping(value = "/subscription")
    public void subscription(@RequestParam(value = "deliveryCorpCode", required = false) String deliveryCorpCode ,
            @RequestParam(value = "trackingNo", required = false) String trackingNo){
	     ShellLogisticsSubscription logisticsSubscription = new ShellLogisticsSubscription();
	     logisticsSubscription.setDeliveryCorpCode(deliveryCorpCode);
	     logisticsSubscription.setTrackingNo(trackingNo);
         logisticsService.subscription(logisticsSubscription);
    }
	
    /**
     * 物流回调方法
     * @param param
     * @param sign
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/callbackurl")
    public Object callbackurl(@RequestParam(value = "param", required = false) String param ,
    		@RequestParam(value = "sign", required = false) String sign){
    	 String str = logisticsService.callbackurl(param, sign);
    	 return str;
    }
    
    @ResponseBody
    @RequestMapping(value = "/test")
    public Object test(){
         return  ConfigService.SCHEMA +"=="+configService.getString(ConfigService.SCHEMA);
    }
    
    
}
