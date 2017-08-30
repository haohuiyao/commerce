package com.meeno.framework.util;

import com.alibaba.fastjson.JSONObject;

public class WechatUtil {
	
	public static void getCode(String STATE) {
		System.out.println("getCode");
		String getCodeUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Constant.WECHAT_APP_ID+"&redirect_uri="+Constant.WECHAT_REDIRECT_URI+"&response_type=code&scope=snsapi_userinfo&state="+STATE+"#wechat_redirect";
		System.out.println(getCodeUrl);
		CommonUtil.httpsRequest(getCodeUrl, "GET", null);
	}
	
	/**
     * 公众号全局接口调用凭证
     * 
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @return 
     */
    public static String getPubAccessToken(String appId, String appSecret) {
        String accessToken = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("APPSECRET", appSecret);
        // 获取授权凭证
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                
                accessToken = jsonObject.getString("access_token");
            } catch (Exception e) {
                accessToken = null;
            }
        }
        return accessToken;
    }
    
    /**
     * 公众号获取jsapi_ticket
     * 
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @return 
     */
    public static String getJsApiTicket(String accessToken) {
        String jsApiTicket = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // 获取jsapi_ticket
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                
            	jsApiTicket = jsonObject.getString("ticket");
            } catch (Exception e) {
            	jsApiTicket = null;
            }
        }
        return jsApiTicket;
    }
	
	
	/**
     * 获取网页授权凭证
     * 
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */
    public static WechatOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        WechatOauth2Token wat = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                wat = new WechatOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInteger("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
            }
        }
        return wat;
    }
    
    /**
     * 通过网页授权获取用户信息
     * 
     * @param accessToken 网页授权接口调用凭证
     * @param openId 用户标识
     * @return SNSUserInfo
     */
    public static WechatUser getUserInfo(String accessToken, String openId) {
    	WechatUser snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                snsUserInfo = new WechatUser();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setGender(String.valueOf(jsonObject.getInteger("sex")));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                snsUserInfo = null;
            }
        }
        return snsUserInfo;
    }
    
}
