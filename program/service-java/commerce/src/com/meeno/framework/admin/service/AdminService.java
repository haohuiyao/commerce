package com.meeno.framework.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.meeno.framework.admin.entity.AdminEntry;
import com.meeno.framework.admin.entity.AdminGroup;
import com.meeno.framework.passport.util.MNPLoginCallback;
import com.meeno.framework.tag.CutPageBean;

/**
* @description TODO
* @time 2017年7月20日 下午3:39:18
* @author Saturn
* @version 1.0
*/
public interface AdminService {
	
	/**
	 * @Description: 管理员登录
	 * @param username
	 * @param password
	 * @param callback
	 * @return void
	 */
	void loginNormal(String username, String password, MNPLoginCallback callback);
	
	/**
	 * @Description: 获取管理员列表
	 * @param pageBean
	 * @return CutPageBean
	 */
	CutPageBean getAdminList(CutPageBean pageBean);
	
	/**
	 * @Description: 添加管理员
	 * @param username
	 * @param password
	 * @param groupId
	 * @return void
	 */
	void addAdmin(String username, String password, Long groupId);
	
	/**
	 * @Description: 修改管理员
	 * @param id
	 * @param username
	 * @param password
	 * @param groupId
	 * @return void
	 */
	void modAdmin(String id, String username, String password, Long groupId);
	
	/**
	 * @Description: 删除管理员
	 * @param adminIdArr
	 * @return void
	 */
	void delAdmin(JSONArray adminIdArr);
	
	/**
	 * @description 根据ID获取用户分组信息
	 * @param id
	 * @return
	 */
	AdminGroup findAdminGroupById(Long id);
	
	/**
	 * @description 根据ID获取管理页面信息
	 * @param id
	 * @return
	 */
	AdminEntry findAdminEntryById(Long id);
}
