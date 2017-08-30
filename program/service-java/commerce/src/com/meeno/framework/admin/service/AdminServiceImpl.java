package com.meeno.framework.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.meeno.framework.admin.dao.AdminEntryDao;
import com.meeno.framework.admin.dao.AdminGroupDao;
import com.meeno.framework.admin.entity.AdminEntry;
import com.meeno.framework.admin.entity.AdminGroup;
import com.meeno.framework.passport.dao.PassportDao;
import com.meeno.framework.passport.entity.MNAccount;
import com.meeno.framework.passport.entity.MNPLoginRecord;
import com.meeno.framework.passport.entity.MNPNormal;
import com.meeno.framework.passport.service.PassportService;
import com.meeno.framework.passport.util.MNPLoginCallback;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.user.dao.UserDao;
import com.meeno.framework.user.entity.BaseUser;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.ErrMsg;
import com.meeno.framework.util.MeenoAssert;
import com.meeno.util.StringContant;

/**
* @description 管理员服务实现
* @time 2017年7月20日 下午3:40:42
* @author Saturn
* @version 1.0
*/
@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private PassportService passportService;
	
	@Autowired 
	private PassportDao passportDao;
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired
	private AdminGroupDao adminGroupDao;
	
	@Autowired
	private AdminEntryDao adminEntryDao;
	
	
	/**
	 * @description 管理员登录
	 * @param username
	 * @param password
	 * @param callback
	 */
	@Override
	public void loginNormal(String username, String password, MNPLoginCallback callback) {		
		
		//登录相关
		MNPLoginRecord loginRecord = this.passportService.loginNormal(username, password, StringContant.ENTRY_TYPE_BACK);
		
		//获取用户信息
		BaseUser baseUser = this.userDao.findUser(loginRecord.getAccount(), "");
		MeenoAssert.notNull(baseUser, ErrMsg.ADMIN_NOT_EXIST);
		
		//登录成功回调
		callback.onSuccess(loginRecord, baseUser);
	}

	/**
	 * @description 获取管理员列表
	 * @param pageBean
	 * @return
	 */
	@Override
	public CutPageBean getAdminList(CutPageBean pageBean) {
		return this.userDao.geUserList(pageBean, StringContant.USER_ADMIN);
	}

	/**
	 * @description 新增管理员
	 * @param username
	 * @param password
	 * @param groupId
	 */
	@Override
	public void addAdmin(String username, String password, Long groupId) {
		
		MNAccount account = new MNAccount();
		account.setStatus(StringContant.STATUS_AVA);
		//新增登录凭证
		this.passportService.saveNormalPP(account, username, password);
		//新增用户
		this.userDao.saveBaseUser(account, username, StringContant.GENDER_MALE, StringContant.USER_ADMIN, groupId);
	}

	/**
	 * @description 修改管理员信息
	 * @param id
	 * @param username
	 * @param password
	 * @param groupId
	 */
	@Override
	public void modAdmin(String id, String username, String password, Long groupId) {
		
		MeenoAssert.hasLength(id, ErrMsg.ADMIN_IDS_NULL);

		BaseUser baseUser = this.userDao.get(BaseUser.class, Long.parseLong(id));
		MeenoAssert.notNull(baseUser, ErrMsg.ADMIN_NOT_EXIST);
		
		MeenoAssert.hasLength(username,ErrMsg.USERNAME_CANT_BE_EMPTY);
		MeenoAssert.hasLength(password,ErrMsg.PWD_CANT_BE_EMPTY);
		MeenoAssert.hasLength(password,ErrMsg.GROUPID_CANT_BE_EMPTY);
		
		MNPNormal pp = this.passportDao.findNormalPassportByName(username);
		MeenoAssert.notNull(pp, ErrMsg.ACCOUNT_NOT_EXIST);
		pp.setPwd(CommonUtil.MD5_32(password));
		this.passportDao.update(pp);
		
		baseUser.setGroupId(groupId);
		this.userDao.update(baseUser);
	}

	/**
	 * @description 删除管理员
	 * @param adminIdArr
	 */
	@Override
	public void delAdmin(JSONArray adminIdArr) {
		// TODO Auto-generated method stub
		MeenoAssert.hasLength(adminIdArr.toJSONString(), ErrMsg.ADMIN_IDS_NULL);
		if(adminIdArr.size()>0){
			for (int i = 0; i < adminIdArr.size(); i++) {
				String id = adminIdArr.getString(i);
				BaseUser baseUser = this.userDao.get(BaseUser.class, Long.parseLong(id));
				MeenoAssert.notNull(baseUser, ErrMsg.ADMIN_NOT_EXIST);
				this.userDao.delete(baseUser);
				
				MNPNormal normal = (MNPNormal) this.passportDao.find(" from MNPNormal n where n.account.uuid=?", baseUser.getAccount().getUuid()).get(0);
				MeenoAssert.notNull(normal, ErrMsg.ACCOUNT_NOT_EXIST);
				
				this.passportDao.delete(normal);
				
				MNPLoginRecord loginRecord = this.passportDao.findLoginRecord(baseUser.getAccount(), StringContant.ENTRY_TYPE_BACK);
				if(loginRecord!=null){
					this.passportDao.delete(loginRecord);
				}
				this.passportDao.delete(baseUser.getAccount());
			}
		}
	}

	/**
	 * @description 根据ID获取用户分组信息
	 * @param id
	 * @return
	 */
	@Override
	public AdminGroup findAdminGroupById(Long id) {
		return this.adminGroupDao.findAdminGroupById(id);
	}

	/**
	 * @description 根据ID获取管理页面信息
	 * @param id
	 * @return
	 */
	@Override
	public AdminEntry findAdminEntryById(Long id) {
		return this.adminEntryDao.findAdminEntryById(id);
	}
}
