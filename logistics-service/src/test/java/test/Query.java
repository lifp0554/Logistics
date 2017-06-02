package test;

import java.util.HashMap;

import com.ideacome.pojo.RealTimeRespnse;
import com.ideacome.util.HttpRequest;
import com.ideacome.util.JacksonHelper;
import com.ideacome.util.MD5;

public class Query {
	public static void main(String[] args) throws Exception {

		String param ="{\"com\":\"yuantong\",\"num\":\"12345678\"}";
		String customer ="54BCEB1353F2085DE9AE8658ABA6191B";
		String key = "iZOaZCeq7552";
		String sign = MD5.encode(param+key+customer);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("param",param);
		params.put("sign",sign);
		params.put("customer",customer);
		String resp;
		try {
			resp = HttpRequest.postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
			RealTimeRespnse response = JacksonHelper.fromJSON(resp, RealTimeRespnse.class);
			System.out.println(response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
