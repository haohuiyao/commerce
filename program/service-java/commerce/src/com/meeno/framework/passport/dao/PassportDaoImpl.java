package com.meeno.framework.passport.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.passport.entity.MNAccount;
import com.meeno.framework.passport.entity.MNPLoginRecord;
import com.meeno.framework.passport.entity.MNPNormal;
import com.meeno.framework.passport.entity.MNPPhone;
import com.meeno.framework.passport.entity.MNPQq;
import com.meeno.framework.passport.entity.MNPWx;
import com.meeno.framework.passport.entity.PhoneVCode;
import com.meeno.util.StringContant;

@Repository
public class PassportDaoImpl extends BaseDaoImpl implements PassportDao {

	/**
	 * 通过用户名查找MNPNormal
	 * 
	 * @param username
	 * @return
	 */
	@Override
	public MNPNormal findNormalPassportByName(String username) {

		List<MNPNormal> list = this.find(" from MNPNormal m where m.username = ? ", username);
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
		List<PhoneVCode> list = this.find("from PhoneVCode p where p.phone = ? and p.vType = ? ",
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
	
	@Override
	public MNPQq findMNPQqByUUID(String uuid) {
		List<MNPQq> list = this.find("from MNPQq m where m.account.uuid = ? ", uuid);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	

	/**
	 * 查找NormalPP
	 * @param uuid
	 * @return
	 */
	@Override
	public MNPNormal findNormalPPByUUID(String uuid) {
		List<MNPNormal> list = this.find("from MNPNormal m where m.account.uuid = ? ", uuid);
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
		MNPNormal mnpnormal = new MNPNormal();
		mnpnormal.setUsername(username);
		mnpnormal.setPwd(pwd);
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

	@Override
	public MNPQq findQQPP(String openId) {
		List<MNPQq> list = this.find("from MNPQq m where m.openId = ? ", openId);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public MNPQq saveQQPP(MNAccount account, String openId) {
		MNPQq mnpQq = new MNPQq();
		mnpQq.setAccount(account);
		mnpQq.setOpenId(openId);
		this.save(mnpQq);
		return mnpQq;
	}

	@Override
	public MNPQq selectQQPP(String account) {
		List<MNPQq> list = this.find(" from MNPQq m where m.account.uuid = ? ", account);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public MNPWx selectWXPP(String account) {
		List<MNPWx> list = this.find(" from MNPWx m where m.account.uuid = ? ", account);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteQQPP(MNPQq mnpQq) {
		this.hibernateTemplate.delete(mnpQq);
	}

	@Override
	public void deleteWXPP(MNPWx mnpWx) {
		this.hibernateTemplate.delete(mnpWx);
	}

}