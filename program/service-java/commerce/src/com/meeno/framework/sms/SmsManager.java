package com.meeno.framework.sms;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SmsManager {

	private final static String URL = "http://xtx.telhk.cn:8080/sms.aspx";
	private final static String USERID = "5349";
	private final static String ACCOUNT = "a10140";
	private final static String PASSWORD = "475237";
//	private final static String content = "验证码为xxxxxx,该验证码10分钟内有效【医家护】";
	
	private final static String DY_URL = "http://gw.api.taobao.com/router/rest";
	private final static String APPKEY = "23487480";
	private final static String SECRET = "228e812f447e548577ad7b8b009f40bf";
	private final static String SMS_TEMPLATE_CODE  = "SMS_21275002";
	
	public static void sendSms(String mobile, String code) {
		String send = SmsClientSend.sendSms(URL,"send",USERID,ACCOUNT,
			PASSWORD,mobile,code);
		try {
			System.out.println(new String(send.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized static void sendALDYSmsCode(String mobile, String code, String signature){
		TaobaoClient client = new DefaultTaobaoClient(DY_URL, APPKEY, SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//		req.setExtend("123456");
		Map<String,String> jsonMap = Maps.newHashMap();
		jsonMap.put("code", code);
		req.setSmsType("normal");
		req.setSmsFreeSignName(signature);
		req.setSmsParamString(JSON.toJSON(jsonMap).toString());
		req.setRecNum(mobile);
		req.setSmsTemplateCode(SMS_TEMPLATE_CODE);
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println(rsp.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized static void sendALDYSmsAlert(String mobile, String code, String signature){
		TaobaoClient client = new DefaultTaobaoClient(DY_URL, APPKEY, SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//		req.setExtend("123456");
		Map<String,String> jsonMap = Maps.newHashMap();
		jsonMap.put("code", code);
		req.setSmsType("normal");
		req.setSmsFreeSignName(signature);
		req.setSmsParamString(JSON.toJSON(jsonMap).toString());
		req.setRecNum(mobile);
		req.setSmsTemplateCode(SMS_TEMPLATE_CODE);
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println(rsp.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
