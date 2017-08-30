package com.meeno.framework.util;

import javax.print.DocFlavor.STRING;

public interface Constant {
	 String VERIFY_CODE_NAME="verifyCode";
	 String FRAMEWORK_VERSION="version.framework";
	 String FORMAT_DATE_STYLE = "format.date.style";
	 String FORMAT_TIME_STYLE = "format.time.style";
	 /***
	  * 默认的系统编码.
	  */
	 String DEFAULT_SYSTEM_CODE = "default";
     /**
	  * 定义系统的title
	  */
	String SYS_TITLE="meeno_framework管理系统";
	 /**
		 * 生效.
		 */
	 String STATUS_ACTIVE = "'A'";

	 /***
	  * AJAX 调用输出的内容名称.
	  * 使用request attribute.
	  */
	String AJAX_OUT_DATA = "_out_data";
	 /***
	  * AJAX 调用出错.
	  */
	String AJAX_ERROR = "_error_info";

	int VALIDATE_ERROR_CODE = 3000;

	 /**
     * 邮箱验证正则表达式.
     */
    String REGULAR_EXPRESSION_EMAIL = "^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";

    /**
	 * 手机验证正则表达式.
	 */
	String REGULAR_EXPRESSION_MOBILENO = "^((13[0-9])|(17[0-9])|(12[0-9])|(16[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
    
	/***
	 * 金额正则表达式.
	 */
	String CASH_EXPRESSION = "^(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?$";
	
    /**
     * 登录类型--前台
     */
    String LOGIN_TYPE_FRONT = "1";

    /**
     * 登录类型--后台
     */
    String LOGIN_TYPE_BACK = "2";
    
    /**
	 * request返回成功
	 */
	Integer RESPONSE_SUCCESS = Integer.valueOf(0);
	
	/**
	 * request返回失败
	 */
	Integer RESPONSE_ERROR = Integer.valueOf(1);

	/**
	 * 地址为空
	 */
	Integer ADDRESS_NULL = Integer.valueOf(102);
	
	/**
	 * request返回未登录错误
	 */
	Integer RESPONSE_NOT_LOGGED = Integer.valueOf(101);
	
	/**
	 * request返回重登录错误
	 */
	Integer RESPONSE_FAILED_RELOGN = Integer.valueOf(103);
	
	/**
	 * request返回账号不存在
	 */
	Integer RESPONSE_NORMALPP_NOT_EXIST = Integer.valueOf(104);
	
	/**
	 * request返回第三方登录未绑定手机号错误
	 */
	Integer RESPONSE_BIND_PHONE_NEEDED = Integer.valueOf(105);
	
	/**
	 * Ping++退款 不能对订单号 *** 发起退款，因为已经全额退款了
	 */
	Integer ORDER_REFUND_ERROR=Integer.valueOf(106);
	
	String MINGS_MALL = "挖配商城";
	String MINGS_MALL_ORDER = "挖配商城订单";
	
	/**
	 * 订单支付有效时长
	 */
	long ORDER_PAYMENT_EFFECTIVE_DURATION = 24*3600*1000;
	
	/**
	 * 订单自动确认收货时间限制
	 */
	long ORDER_CONFIRM_RCV_TIME_LIMIT = 24*3600*1000*7;
	
	/**
	 * 订单自动好评时间限制
	 */
	long ORDER_AUTO_COMMENT_TIME_LIMIT = 24*3600*1000*7;
	
	/**
	 * 收货后允许申请售后时长
	 */
	long APPLY_REFUND_PERM_TIME_LIMIT = 24*3600*1000*30;
	
	/**
	 * wechat
	 */
	String WECHAT_APP_ID = "wxc7d3fbd794e46a75";
	String WECHAT_APP_SECRET = "52f88f21707ae643f68f32d00a513871";
	String WECHAT_REDIRECT_URI = "";
	String WECHAT_GENDER_MALE = "1";
	String WECHAT_GENDER_FEMALE = "2";
	
	/**
	 * Ping++退款 不能对订单号 *** 发起退款，因为已经全额退款了
	 */
	String INVALID_REQUEST_ERROR="invalid_request_error";
}
