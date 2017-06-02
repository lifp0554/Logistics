package com.ideacome.logistics.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ideacome.logistics.entity.ShellLogisticsSubscription;
import com.ideacome.logistics.entity.ShellLogisticsSubscriptionExample;
import com.ideacome.logistics.entity.ShellLogisticsWithBLOBs;
import com.ideacome.logistics.mapper.ShellLogisticsMapper;
import com.ideacome.logistics.mapper.ShellLogisticsSubscriptionMapper;
import com.ideacome.pojo.*;
import com.ideacome.util.HttpRequest;
import com.ideacome.util.JacksonHelper;
import com.ideacome.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class LogisticsServiceImpl implements LogisticsService{
	
	@Autowired
	private ConfigService configService;
	@Autowired
	private ShellLogisticsSubscriptionMapper subscriptionMapper;
	@Autowired
	private ShellLogisticsMapper shellLogisticsMapper;
	
	private static Logger LOGGER = LoggerFactory.getLogger(LogisticsServiceImpl.class);
	
	//查询所有订阅未成功的请求
	@Override
	public List<Map<String,Object>> selectSubscriptionNotSuccess(){
	    LOGGER.info("查询所有订阅未成功的请求");
		List<Map<String,Object>> resultList = null;
		ShellLogisticsSubscription subscription = new ShellLogisticsSubscription();
		subscription.setResult("false");
		List<ShellLogisticsSubscription> list = subscriptionMapper.selectByConditions(subscription);
		if(list!=null && list.size()>0){
			resultList = new ArrayList<>();
			for(ShellLogisticsSubscription subscript : list){
				Map<String,Object> map = new HashMap<>();
				map.put("orderId", subscript.getOrderId());
				map.put("shppingId", subscript.getShippingId());
				map.put("trackingNo", subscript.getTrackingNo());
				map.put("deliveryCorpCode", subscript.getDeliveryCorpCode());
				resultList.add(map);
			}
		}
		LOGGER.info("所有订阅未成功的请求:" + JSON.toJSONString(resultList));
		return resultList;
	}
	
	//根据物流单号查询运单物流信息
	@SuppressWarnings("unchecked")
    @Override
	public Map<String,Object> selectLogisticsinfo(String trackingNo){
	    LOGGER.info("根据物流单号查询运单物流信息:"+ JSON.toJSONString(trackingNo));
		Map<String,Object> resultMap = null;
		ShellLogisticsWithBLOBs logistics = shellLogisticsMapper.selectByTrackingNo(trackingNo);
		if(logistics !=null){
			resultMap = new HashMap<>();
			resultMap.put("trackingNo", logistics.getTrackingNo());
			resultMap.put("deliveryCorpCode", logistics.getDeliveryCorpCode());
			resultMap.put("state", logistics.getState());
			resultMap.put("destNu", logistics.getDestNu());
			resultMap.put("destCom", logistics.getDestCom());
			resultMap.put("destState", logistics.getDestState());
			
			if(StringUtils.isNotBlank(logistics.getLastContext())){
		        List<Object> list = JSON.parseArray(logistics.getLastContext(), Object.class);
		        List<Map<String,Object>> lastContext = new ArrayList<>();
		        for (Object map : list) { 
		        	Map<String,Object> context = new HashMap<>();
		            for (Map.Entry<String,Object> entry : ((Map<String,Object>)map).entrySet()) {
		            	context.put(entry.getKey(), entry.getValue());
			        }
		            lastContext.add(context);
		        } 
		        resultMap.put("lastContext", lastContext);
			}
			if(StringUtils.isNotBlank(logistics.getDestContext())){
				List<Object> list = JSON.parseArray(logistics.getDestContext(), Object.class);  
				List<Map<String,Object>> destContext = new ArrayList<>();
		        for (Object map : list) { 
		        	Map<String,Object> context = new HashMap<>();
		            for (Map.Entry<String,Object> entry : ((Map<String,Object>)map).entrySet()) {
		            	context.put(entry.getKey(), entry.getValue());
			        }
		            destContext.add(context);
		        } 
		        resultMap.put("destContext", destContext);
			}	
		}
		LOGGER.info("根据物流单号查询运单物流信息结果:"+ JSON.toJSONString(resultMap));
		return resultMap;
	}

	@Override
	public Map<String, Object> selectLogisticsSubscriptionInfo(String trackingNo) {
	    Map<String, Object> result = new HashMap<>();
	    
	    ShellLogisticsSubscriptionExample example = new ShellLogisticsSubscriptionExample();
	    example.createCriteria().andTrackingNoEqualTo(trackingNo);
	    
	    List<ShellLogisticsSubscription> subscriptions = subscriptionMapper.selectByExample(example);
	    if(!CollectionUtils.isEmpty(subscriptions)){
	        ShellLogisticsSubscription subscription = subscriptions.get(0);
	        
	        result.put("result", subscription.getResult());
	        result.put("returnCode", subscription.getReturnCode());
	        result.put("message", subscription.getMessage());

	        return result;
	    }
	    
	    return null;
	}

	@Override
	public Map<String, Object> subscription(String trackingNo, String deliveryCorpCode){
		Map<String, Object> subscriptionResult = new HashMap<>();
		// 如果已订阅成功，则放弃订阅; 否则请求快递100订阅接口
		Map<String, Object> selectLogisticsResult = selectLogisticsSubscriptionInfo(trackingNo);
		if (null != selectLogisticsResult && "success".equals(selectLogisticsResult.get("result"))){
			subscriptionResult.put("result", selectLogisticsResult.get("result"));
			subscriptionResult.put("returnCode", selectLogisticsResult.get("returnCode"));
			subscriptionResult.put("message", selectLogisticsResult.get("message"));
			LOGGER.info("订阅运单物流信息: 已订阅成功, 放弃订阅");
			return subscriptionResult;
		}
		ShellLogisticsSubscription shellLogisticsSubscription = new ShellLogisticsSubscription();
		shellLogisticsSubscription.setTrackingNo(trackingNo);
		shellLogisticsSubscription.setDeliveryCorpCode(deliveryCorpCode);
		return subscription(shellLogisticsSubscription);
	}

	@Override
	public Map<String, Object> subscription(ShellLogisticsSubscription logisticsSubscription){
	    LOGGER.info("订阅运单物流信息:"+ JSON.toJSONString(logisticsSubscription));
		Map<String, Object> subscriptionResult = new HashMap<>();
		//组装订阅请求参数
		TaskRequest req = new TaskRequest();
		req.setNumber(logisticsSubscription.getTrackingNo());//物流单号
		if(logisticsSubscription.getDeliveryCorpCode() != null){
		    req.setCompany(logisticsSubscription.getDeliveryCorpCode());
		}else{
		    String comCode = queryComCode(logisticsSubscription.getTrackingNo());
		    if(StringUtils.isNotBlank(comCode)){
				logisticsSubscription.setDeliveryCorpCode(comCode);
				req.setCompany(comCode);
			}
		}
		req.setKey( configService.getString(ConfigService.KEY));//授权密匙(Key)
		req.getParameters().put("callbackurl", configService.getString(ConfigService.CALLBACK_URL));
		req.getParameters().put("resultv2", "1");//添加此字段表示开通行政区域解析功能
		req.getParameters().put("autoCom", "1");//添加此字段且将此值设为1，则表示开始智能判断单号所属公司的功能，开启后，company字段可为空,即只传运单号（number字段），我方收到后会根据单号判断出其所属的快递公司（即company字段）。建议只有在无法知道单号对应的快递公司（即company的值）的情况下才开启此功能；
		req.getParameters().put("interCom", "1");//添加此字段表示开启国际版，开启后，若订阅的单号（即number字段）属于国际单号，会返回出发国与目的国两个国家的跟踪信息，出发国的跟踪信息见2.3.1中的lastResult元素组，目的国的跟踪信息见2.3.2中的destResult元素组。本功能暂时只支持邮政体系（国际类的邮政小包、EMS）内的快递公司，若单号我方识别为非国际单，即使添加本字段，也不会返回destResult元素组。建议对于附件文档《快递100快递公司编码.xlsx》中有国家编码的公司通道都添加此字段。
		
		HashMap<String, String> param = new HashMap<String, String>(); 
		param.put("schema", configService.getString(ConfigService.SCHEMA));
		param.put("param", JacksonHelper.toJSON(req));
		
		LOGGER.info(logisticsSubscription.getTrackingNo() + " 订阅请求报文:"+ JSON.toJSONString(param));
		try {
			//调用订阅接口
			String result = HttpRequest.postData(configService.getString(ConfigService.SUBSCRIPTION_URL), param, "UTF-8");
			LOGGER.info("订阅请求返回结果:"+ JSON.toJSONString(result));
			TaskResponse resp = JacksonHelper.fromJSON(result, TaskResponse.class);
			//保存订阅请求结果
			if(resp != null){
				ShellLogisticsSubscription subscription =subscriptionMapper.selectByTrackingNo(logisticsSubscription.getTrackingNo());
				if(subscription == null){
					subscription = new ShellLogisticsSubscription();
					subscription.setCreateDate(new Date());
					subscription.setVersion(1l);
                    subscription.setOrderId(null == logisticsSubscription.getOrderId() ? 0L
                                    : logisticsSubscription.getOrderId());// 兑换订单ID
                    subscription.setShippingId(null == logisticsSubscription.getShippingId() ? 0L
                                    : logisticsSubscription.getShippingId());// 运单ID
					subscription.setTrackingNo(logisticsSubscription.getTrackingNo());//快递单号
					subscription.setDeliveryCorpCode(logisticsSubscription.getDeliveryCorpCode());//物流公司代码
					subscription.setResult(resp.getResult()?"true":"false");
					subscription.setReturnCode(resp.getReturnCode());
					subscription.setMessage(resp.getMessage());
					subscriptionMapper.insert(subscription);
				}else{
					subscription.setModifyDate(new Date());
					subscription.setVersion(subscription.getVersion()+1);
					subscription.setResult(resp.getResult()?"true":"false");
					subscription.setReturnCode(resp.getReturnCode());
					subscription.setMessage(resp.getMessage());
					subscriptionMapper.updateByPrimaryKeySelective(subscription);
				}
				subscriptionResult.put("result", subscription.getResult());
				subscriptionResult.put("returnCode", subscription.getReturnCode());
				subscriptionResult.put("message", subscription.getMessage());
				return subscriptionResult;
			}
		} catch (Exception e) {
		    LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	@Override
	public String callbackurl(String param,String sign){
		//返回结果
		NoticeResponse resp = new NoticeResponse();
		resp.setResult(false);
		resp.setReturnCode("500");
		resp.setMessage("回调失败，无返回结果");
		LOGGER.info("物流回调结果:"+ JSON.toJSONString(param));
		try {
		    if(param!=null && StringUtils.isNotBlank(param)){
	            NoticeRequest noticeRequest = JacksonHelper.fromJSON(param,NoticeRequest.class);
	            if(noticeRequest != null){
	                Result lastResult = noticeRequest.getLastResult();
	                Result destResult = noticeRequest.getDestResult();
	                if(lastResult!=null && lastResult.getNu() != null){
	                    //判断快递公司代码是否正确
	                    if( noticeRequest.getAutoCheck()!=null && noticeRequest.getComNew()!=null){
	                        //TODO 修改快递公司代码
	                        Map<String,Object> paramMap = new HashMap<>();
	                        paramMap.put("deliveryCorpCode", noticeRequest.getComNew());
	                        paramMap.put("trackingNo", lastResult.getNu());
	                        //logisticsMapper.updateShellShipping(paramMap);
	                    }
	                    //保存回调结果
	                    ShellLogisticsWithBLOBs shellLogistics = shellLogisticsMapper.selectByTrackingNo(lastResult.getNu());
	                    if(shellLogistics == null){
	                        shellLogistics = new ShellLogisticsWithBLOBs();
	                        shellLogistics.setCreateDate(new Date());
	                        shellLogistics.setVersion(1l);
	                        if(lastResult.getState() != null)
	                            shellLogistics.setState(new Integer(lastResult.getState()));
	                        if(lastResult.getCom() != null)
	                            shellLogistics.setDeliveryCorpCode(lastResult.getCom());
	                        if(lastResult.getNu() != null)
	                            shellLogistics.setTrackingNo(lastResult.getNu());
	                        if(lastResult.getData() != null)
	                            shellLogistics.setLastContext(JacksonHelper.toJSON(lastResult.getData()));
	                        
	                        if(destResult!=null){
	                            if(destResult.getState() != null)
	                                shellLogistics.setDestState(new Integer(destResult.getState()));
	                            if(destResult.getCom() != null)
	                                shellLogistics.setDestCom(destResult.getCom());
	                            if(destResult.getNu() != null)
	                                shellLogistics.setDestNu(destResult.getNu());
	                            if(destResult.getData() != null)
	                                shellLogistics.setDestContext(JacksonHelper.toJSON(destResult.getData()));
	                        }
	                        shellLogisticsMapper.insert(shellLogistics);
	                    }else{
	                        shellLogistics.setModifyDate(new Date());
	                        shellLogistics.setVersion(shellLogistics.getVersion()+1);
	                        if(lastResult.getState() != null)
	                            shellLogistics.setState(new Integer(lastResult.getState()));
	                        if(lastResult.getCom() != null)
	                            shellLogistics.setDeliveryCorpCode(lastResult.getCom());
	                        if(lastResult.getNu() != null)
	                            shellLogistics.setTrackingNo(lastResult.getNu());
	                        if(lastResult.getData() != null)
	                            shellLogistics.setLastContext(JacksonHelper.toJSON(lastResult.getData()));
	                        
	                        if(destResult!=null){
	                            if(destResult.getState() != null)
	                                shellLogistics.setDestState(new Integer(destResult.getState()));
	                            if(destResult.getCom() != null)
	                                shellLogistics.setDestCom(destResult.getCom());
	                            if(destResult.getNu() != null)
	                                shellLogistics.setDestNu(destResult.getNu());
	                            if(destResult.getData() != null)
	                                shellLogistics.setDestContext(JacksonHelper.toJSON(destResult.getData()));
	                        }
	                        shellLogisticsMapper.updateByPrimaryKeySelective(shellLogistics);
	                    }
	                    resp.setResult(true);
	                    resp.setReturnCode("200");
	                    resp.setMessage("回调成功");
	                }
	            }
	        }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
		LOGGER.info("回调返回结果:"+ JSON.toJSONString(resp));
		return JSONObject.toJSONString(resp);
	}
	
	@Override
	public String query(ShellLogisticsSubscription logisticsSubscription) {
		String param ="{\"com\":\""+logisticsSubscription.getDeliveryCorpCode()+"\",\"num\":\""+logisticsSubscription.getTrackingNo()+"\"}";
		String sign = MD5.encode(param + configService.getString(ConfigService.KEY) + configService.getString(ConfigService.CUSTOMER));
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("param",param);
		params.put("sign",sign);
		params.put("customer",configService.getString(ConfigService.CUSTOMER));
		LOGGER.info("实时查询物流信息请求报文:"+ JSON.toJSONString(params));
		String resp = null;
		try {
			resp = HttpRequest.postData(configService.getString(ConfigService.REALTIME_URL), params, "utf-8").toString();
			//RealTimeRespnse response = JacksonHelper.fromJSON(resp, RealTimeRespnse.class);
			LOGGER.info("实时查询物流信息返回结果:"+ JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	@Override
	public String queryComCode(String trackingNo) {
	    HashMap<String, String> params = new HashMap<String, String>();
        params.put("num",trackingNo);
        params.put("key",configService.getString(ConfigService.KEY));
        LOGGER.info("查询快递公司代码:"+ JSON.toJSONString(params));
        try {
            String resp = HttpRequest.postData(configService.getString(ConfigService.COMPANY_CODE_URL), params, "utf-8").toString();
            LOGGER.info("查询快递公司代码返回结果:"+ JSON.toJSONString(resp));
            
            JSONArray json = JSONArray.parseArray(resp);
            return json.getJSONObject(0).getString("comCode");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
	}
	
	
}
