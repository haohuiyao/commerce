package com.meeno.util;

public interface StringContant {

	/**
	 * 生效
	 */
	String STATUS_AVA = "A";

	/**
	 * 失效
	 */
	String STATUS_DIS = "D";

	/**
	 * 入口类型 -- 微信端
	 */
	String ENTRY_TYPE_WX = "1";
	
	/**
	 * 入口类型 -- 管理后台
	 */
	String ENTRY_TYPE_BACK = "2";

	/**
	 * 全部分类名
	 */
	String CATEGORY_NAME_ALL = "全部";

	/**
	 * 参数-- 分类顺序
	 */
	String CONFIG_CATEGORY_ORDER = "category_order";
	
	
	/**
	 * 性别 -- 男
	 */
	String GENDER_MALE = "1";
	
	/**
	 * 性别 -- 女
	 */
	String GENDER_FEMALE = "0";
	
	/**
	 * 用户类型 -- 管理员
	 */
	String USER_ADMIN = "0";
	
	/**
	 * 用户类型 -- 普通用户
	 */
	String USER_COMMON = "1";
	
	/**
	 * 用户分组 -- 默认无分组
	 */
	String GROUP_NONE = "0";
}
