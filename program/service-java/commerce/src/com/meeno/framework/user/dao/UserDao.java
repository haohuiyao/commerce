package com.meeno.framework.user.dao;

import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.passport.entity.MNAccount;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.user.entity.BaseUser;

public interface UserDao extends BaseDao{
	
	
	/**
	 * 查找用户信息
	 * @param account
	 * @param userType
	 * @return
	 */
	BaseUser findUser(MNAccount account, String userType);
	
	/** 
	 * @description 新增普通用户
	 * @param account
	 * @param name
	 * @param gender
	 * @param userType
	 * @param groupId
	 * @return
	 */
	BaseUser saveBaseUser(MNAccount account, String name, String gender, String userType, Long groupId);
	
	/**
	 * @description 获取用户列表
	 * @param pageBean
	 * @param userType
	 * @return
	 */
	CutPageBean geUserList(CutPageBean pageBean, String userType);
}