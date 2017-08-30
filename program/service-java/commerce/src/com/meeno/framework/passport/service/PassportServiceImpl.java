package com.meeno.framework.passport.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.passport.dao.PassportDao;
import com.meeno.framework.passport.entity.MNAccount;
import com.meeno.framework.passport.entity.MNPLoginRecord;
import com.meeno.framework.passport.entity.MNPNormal;
import com.meeno.framework.passport.entity.MNPPhone;
import com.meeno.framework.passport.entity.MNPWx;
import com.meeno.framework.passport.entity.PhoneVCode;
import com.meeno.framework.passport.util.MNPLoginCallback;
import com.meeno.framework.user.dao.UserDao;
import com.meeno.framework.user.entity.BaseUser;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.ErrMsg;
import com.meeno.framework.util.MeenoAssert;
import com.meeno.util.StringContant;

@Service
public class PassportServiceImpl extends BaseDaoImpl implements PassportService {

	@Autowired
	public UserDao userDao;
	
	@Autowired
	public PassportDao passportDao;
	
	/**
	 * 通过用户名查找MNPNormal
	 * 
	 * @param username
	 * @return
	 */
	@Override
	public MNPNormal findNormalPassportByName(String username) {

		List<MNPNormal> list = this.find("from MNPNormal m where m.username = ? ", username);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 通过用户名查找MNPPhoneVCode
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public MNPPhone findPhonePPByPhone(String phone) {
		List<MNPPhone> list = this.find("from MNPPhone m where m.phone = ? ", phone);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 通过openId查找MNPWx
	 * 
	 * @param openId
	 *            微信用户唯一标识
	 * @return
	 */
	@Override
	public MNPWx findWXPP(String openId) {
		List<MNPWx> list = this.find("from MNPWx m where m.openId = ? ", openId);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查找验证码
	 * 
	 * @param phone
	 *            手机号
	 * @param vType
	 *            验证类型
	 * @return
	 */
	@Override
	public PhoneVCode findPhoneVcode(String phone, String vType) {
		List<PhoneVCode> list = this.find(" from PhoneVCode p where p.phone = ? and p.vType = ? ",
				new Object[] { phone, vType });
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查找账号登录信息
	 * 
	 * @param account
	 *            账号
	 * @param entryType
	 *            入口类型
	 * @return
	 */
	@Override
	public MNPLoginRecord findLoginRecord(MNAccount account, String entryType) {
		List<MNPLoginRecord> list = this.find("from MNPLoginRecord m where m.account.uuid = ? and m.entryType = ? ",
				new Object[] { account.getUuid(), entryType });
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 通过UUID查找微信信息
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public MNPWx findMNPWxByUUID(String uuid) {
		List<MNPWx> list = this.find("from MNPWx m where m.account.uuid = ? ", uuid);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 新增账号密码PP
	 * 
	 * @param account
	 * @param username
	 * @param pwd
	 * @return
	 */
	@Override
	public MNPNormal saveNormalPP(MNAccount account, String username, String pwd) {
		
		// 参数检查
		MeenoAssert.hasLength(username, ErrMsg.USERNAME_CANT_BE_EMPTY);
		MeenoAssert.hasLength(pwd, ErrMsg.PWD_CANT_BE_EMPTY);

		MNPNormal pp = passportDao.findNormalPassportByName(username);
		if (pp != null) {
			throw new BusinessRuntimeException(ErrMsg.ACCOUNT_ALREADY_EXIST);
		}

		this.passportDao.save(account);
		
		// 新增账号密码登录的pp
		MNPNormal mnpnormal = new MNPNormal();
		mnpnormal.setUsername(username);
		mnpnormal.setPwd(CommonUtil.MD5_32(pwd));
		mnpnormal.setAccount(account);
		this.save(mnpnormal);
		return mnpnormal;
	}

	/**
	 * 新增PhonePP
	 * 
	 * @param account
	 * @param username
	 * @param pwd
	 * @return
	 */
	@Override
	public MNPPhone savePhonePP(MNAccount account, String phone) {
		MNPPhone mnpphone = new MNPPhone();
		mnpphone.setPhone(phone);
		mnpphone.setAccount(account);
		this.save(mnpphone);
		return mnpphone;
	}

	/**
	 * 新增WxPP
	 * 
	 * @param account
	 * @param openId
	 * @return
	 */
	@Override
	public MNPWx saveWxPP(MNAccount account, String openId) {
		MNPWx mnpWx = new MNPWx();
		mnpWx.setAccount(account);
		mnpWx.setOpenId(openId);
		this.save(mnpWx);
		return mnpWx;
	}

	/**
	 * 新增或更新登录记录
	 * 
	 * @param account
	 * @param entryType
	 * @param token
	 * @return
	 */
	@Override
	public MNPLoginRecord saveOrUpdateLoginRecord(MNAccount account, String entryType, String token) {
		MNPLoginRecord loginRecord = new MNPLoginRecord();
		loginRecord.setAccount(account);
		loginRecord.setEntryType(StringContant.ENTRY_TYPE_WX);
		loginRecord.setToken(token);
		// 更新最近登录时间
		loginRecord.setLastLoginTime(new Date());
		this.saveOrupdate(loginRecord);
		return loginRecord;
	}

	/**
	 * @Description: 验证登录并返回
	 * @param username
	 * @param password
	 * @param entryType
	 * @param callback
	 * @return MNPLoginRecord
	 * @author Saturn
	 * @date 2017年7月21日上午11:35:47
	 */
	@Override
	public MNPLoginRecord loginNormal(String username, String password, String entryType) {
		
		// 参数检查
		MeenoAssert.hasLength(username, ErrMsg.USERNAME_CANT_BE_EMPTY);
		MeenoAssert.hasLength(password, ErrMsg.PWD_CANT_BE_EMPTY);

		// 检查账号是否存在
		MNPNormal pp = this.findNormalPassportByName(username);
		MeenoAssert.notNull(pp, ErrMsg.ACCOUNT_NOT_EXIST);

		// 检查密码是否正确
		if (!CommonUtil.MD5_32(password).equals(pp.getPwd())) {
			throw new BusinessRuntimeException(ErrMsg.PWD_NOT_CORRECT);
		}
		
		MNAccount account = pp.getAccount();
		// 更新账号登录记录
		MNPLoginRecord loginRecord = passportDao.findLoginRecord(account, entryType);
		if (loginRecord == null) {
			loginRecord = new MNPLoginRecord();
			loginRecord.setAccount(account);
			loginRecord.setEntryType(entryType);
		}
		
		// 生成新token
		String newToken = UUID.randomUUID().toString();
		loginRecord.setToken(newToken);
		// 更新最近登录时间
		loginRecord.setLastLoginTime(new Date());
		this.passportDao.saveOrupdate(loginRecord);
		
		return loginRecord;
	}
}