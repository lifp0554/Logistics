package test;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ideacome.logistics.entity.ShellLogisticsSubscription;
import com.ideacome.logistics.service.LogisticsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext.xml"})
@WebAppConfiguration
public class LogisticsServiceImplTest {
    @Autowired
    private LogisticsService logisticsService;
    
    
    @SuppressWarnings("unchecked")
	//@Test
    public void selectLogisticsinfo() {
    	Map<String,Object> param = logisticsService.selectLogisticsinfo("V030344422");
    	List<Map<String,Object>> lastContext = (List<Map<String, Object>>) param.get("lastContext");
    	List<Map<String,Object>> destContext = (List<Map<String, Object>>) param.get("destContext");
    	System.out.println("---------------------------------\n"+lastContext.size());
    	System.out.println("---------------------------------\n"+lastContext.get(2).get("context"));
    	System.out.println("---------------------------------\n"+destContext.get(0).get("context"));
    }
    
	//@Test
    public void selectSubscriptionNotSuccess() {
    	List<Map<String,Object>> list = logisticsService.selectSubscriptionNotSuccess();
    	System.out.println("---------------------------------\n"+list.size());
    	System.out.println("---------------------------------\n"+list);
    }
   
//    @Test
    public void callbackurl() {
    	String param ="{\"status\":\"shutdown\",\"billstatus\":\"check\",\"message\":\"\",\"autoCheck\":\"0\",\"comOld\":\"\",\"comNew\":\"\","+
		"\"lastResult\":{\"message\":\"ok\",\"nu\":\"504075299146\",\"ischeck\":\"1\",\"condition\":\"F00\",\"com\":\"shunfeng\",\"status\":\"200\",\"state\":\"3\","+
		"\"data\":[{\"time\":\"2017-03-21 15:38:36\",\"ftime\":\"2017-03-21 15:38:36\",\"context\":\"已签收,感谢使用顺丰,期待再次为您服务\"},"+
		"{\"time\":\"2017-03-21 13:29:59\",\"ftime\":\"2017-03-21 13:29:59\",\"context\":\"正在派送途中,请您准备签收(派件人:杨家文,电话:18928866224)\"},"+
		"{\"time\":\"2017-03-21 13:23:02\",\"ftime\":\"2017-03-21 13:23:02\",\"context\":\"快件到达 【广州市陶育路营业点】\"},"+
		"{\"time\":\"2017-03-21 10:48:37\",\"ftime\":\"2017-03-21 10:48:37\",\"context\":\"快件在【广州新塘集散中心】已装车，准备发往 【广州市陶育路营业点】\"},"+
		"{\"time\":\"2017-03-21 10:14:54\",\"ftime\":\"2017-03-21 10:14:54\",\"context\":\"快件到达 【广州新塘集散中心】\"},"+
		"{\"time\":\"2017-03-21 08:43:11\",\"ftime\":\"2017-03-21 08:43:11\",\"context\":\"快件在【深圳集散中心】已装车，准备发往 【广州新塘集散中心】\"},"+
		"{\"time\":\"2017-03-21 08:39:55\",\"ftime\":\"2017-03-21 08:39:55\",\"context\":\"快件到达 【深圳集散中心】\"},"+
		"{\"time\":\"2017-03-21 02:05:03\",\"ftime\":\"2017-03-21 02:05:03\",\"context\":\"快件在【杭州总集散中心】已装车，准备发往 【深圳集散中心】\"},"+
		"{\"time\":\"2017-03-21 01:08:05\",\"ftime\":\"2017-03-21 01:08:05\",\"context\":\"快件到达 【杭州总集散中心】\"},"+
		"{\"time\":\"2017-03-20 21:05:01\",\"ftime\":\"2017-03-20 21:05:01\",\"context\":\"快件在【杭州下沙中转场】已装车，准备发往 【杭州总集散中心】\"},"+
		"{\"time\":\"2017-03-20 20:43:16\",\"ftime\":\"2017-03-20 20:43:16\",\"context\":\"快件到达 【杭州下沙中转场】\"},"+
		"{\"time\":\"2017-03-20 19:26:03\",\"ftime\":\"2017-03-20 19:26:03\",\"context\":\"快件在【杭州市莲花营业点】已装车，准备发往 【杭州下沙中转场】\"},"+
		"{\"time\":\"2017-03-20 16:29:22\",\"ftime\":\"2017-03-20 16:29:22\",\"context\":\"顺丰速运 已收取快件\"}]}}";
    	
    	String ss = logisticsService.callbackurl(param,null);
    	System.out.println("-------------------------"+ss);
    }
    
    
//    @Test
    public void query() {
    	ShellLogisticsSubscription logisticsSubscription = new ShellLogisticsSubscription();
    	logisticsSubscription.setTrackingNo("500389220457");
    	logisticsSubscription.setDeliveryCorpCode("yuantong");
    	String ret = logisticsService.query(logisticsSubscription);
    	System.out.println("-------------------------"+ret);
    }
    
    
   // @Test
    public void subscription() {
    	ShellLogisticsSubscription logisticsSubscription = new ShellLogisticsSubscription();
    	
    	logisticsSubscription.setOrderId(11l);
    	logisticsSubscription.setShippingId(11l);
    	logisticsSubscription.setTrackingNo("500389220457");
    	logisticsSubscription.setDeliveryCorpCode("yuantong");
    	logisticsService.subscription(logisticsSubscription);
    	
    	logisticsSubscription.setTrackingNo("500389220456");
    	logisticsSubscription.setDeliveryCorpCode("yuantong");
    	logisticsService.subscription(logisticsSubscription);
    	
    	logisticsSubscription.setTrackingNo("1202389136810");
    	logisticsSubscription.setDeliveryCorpCode("yunda");
    	logisticsService.subscription(logisticsSubscription);
    }
    
    @Test
    public void queryComCode() {
        String ret = logisticsService.queryComCode("500389220457");
        System.out.println("-----------查询快递公司代码：queryComCode()--------------"+ret);
    }
}
