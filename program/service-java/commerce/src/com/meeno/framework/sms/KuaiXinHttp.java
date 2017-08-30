package com.meeno.framework.sms;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class KuaiXinHttp {
	private static String Url="http://sh2.cshxsp.com/sms.aspx?action=send";
	
	public static void sendSms(String phone,String code){
		sendHttp(phone, code);
	}
	
	private static void sendHttp(String phone,String code){
		//String Text=URLEncoder.encode("您的验证码：8859【华信】","utf-8");
		String Text="您的短信验证码是"+code+" 【挖配网】";
		
		try {
		HttpClient client=new HttpClient();
		PostMethod post=new PostMethod(Url);
		post.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		NameValuePair userid=new NameValuePair("userid","");
		NameValuePair account=new NameValuePair("account","jksc807");
		NameValuePair password=new NameValuePair("password","rty789");
		NameValuePair mobile=new NameValuePair("mobile",phone);
		NameValuePair content=new NameValuePair("content",Text);
		NameValuePair sendTime=new NameValuePair("sendTime","");
		NameValuePair extno=new NameValuePair("extno","");
		post.setRequestBody(new NameValuePair[]{userid,account,password,mobile,content,sendTime,extno});
		int statu=client.executeMethod(post);
		System.out.println("statu="+statu);
		String str=post.getResponseBodyAsString();
		System.out.println(str);
			//将字符转化为XML
			Document doc=DocumentHelper.parseText(str);
			//获取根节点
			Element rootElt=doc.getRootElement();
			//获取根节点下的子节点的值
			String returnstatus=rootElt.elementText("returnstatus").trim();
			String message=rootElt.elementText("message").trim();
			String remainpoint=rootElt.elementText("remainpoint").trim();
			String taskID=rootElt.elementText("taskID").trim();
			String successCounts=rootElt.elementText("successCounts").trim();
			
			System.out.println("返回状态为："+returnstatus);
			System.out.println("返回信息提示："+message);
			System.out.println("返回余额："+remainpoint);
			System.out.println("返回任务批次："+taskID);
			System.out.println("返回成功条数："+successCounts);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
