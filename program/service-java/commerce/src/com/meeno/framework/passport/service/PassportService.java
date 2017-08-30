package com.meeno.framework.passport.service;

import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.passport.entity.MNAccount;
import com.meeno.framework.passport.entity.MNPLoginRecord;
import com.meeno.framework.passport.entity.MNPNormal;
import com.meeno.framework.passport.entity.MNPPhone;
import com.meeno.framework.passport.entity.MNPWx;
import com.meeno.framework.passport.entity.PhoneVCode;
import com.meeno.framework.passport.util.MNPLoginCallback;


public interface PassportService extends BaseDao{
	
	
	/**
	 * 通过用户名查找MNPNormal
	 * @param username
	 * @return
	 */
	MNPNormal findNormalPassportByName(String username);
	
	/**
	 * 通过用户名查找MNPPhoneVCode
	 * @param phone
	 * @return
	 */
	MNPPhone findPhonePPByPhone(String phone);
	
	/**
	 * 通过openId查找MNPWx
	 * @param openId
	 * 			微信用户唯一标识
	 * @return
	 */
	MNPWx findWXPP(String openId);
	
	/**
	 * 查找账号登录信息
	 * @param account
	 * 			账号
	 * @param entryType
	 * 			入口类型
	 * @return
	 */
	MNPLoginRecord findLoginRecord(MNAccount account, String entryType);
	
	/**
	 * 查找验证码
	 * @param phone
	 * 			手机号
	 * @param vType
	 * 			验证类型
	 * @return
	 */
	PhoneVCode findPhoneVcode(String phone, String vType);
		
	/**
	 * 查找微信PP
	 * @param uuid
	 * @return
	 */
	MNPWx findMNPWxByUUID(String uuid);
	
	/**
	 * 新增账号密码PP
	 * @param account
	 * @param username
	 * @param pwd
	 * @return
	 */
	MNPNormal saveNormalPP(MNAccount account, String username, String pwd);
	
	/**
	 * 新增PhonePP
	 * @param account
	 * @param username
	 * @param pwd
	 * @return
	 */
	MNPPhone savePhonePP(MNAccount account, String phone);
	
	/**
	 * 新增WxPP
	 * @param account
	 * @param openId
	 * @return
	 */
	MNPWx saveWxPP(MNAccount account, String openId);
	
	/**
	 * 新增或更新登录记录
	 * @param account
	 * @param entryType
	 * @param token
	 * @return
	 */
	MNPLoginRecord saveOrUpdateLoginRecord(MNAccount account, String entryType, String token);
	
	/**
	 * @Description: 普通方式登录
	 * @param username
	 * @param password
	 * @param entryType
	 * @param callback
	 * @return MNPLoginRecord
	 * @author Saturn
	 * @date 2017年7月21日下午2:36:37
	 */
	MNPLoginRecord loginNormal(String username, String password, String entryType);
}