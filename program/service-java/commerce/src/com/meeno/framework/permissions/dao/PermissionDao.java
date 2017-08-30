package com.meeno.framework.permissions.dao;

import java.util.List;

import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.permissions.entity.Action;
import com.meeno.framework.permissions.entity.Role;

public interface PermissionDao extends BaseDao {

	
	/**
	 * 获取权限
	 * @param type
	 * @return
	 */
	List<Action> getActionList(String type);
	

	/**
	 * 获取二级权限
	 * @param id
	 * @return
	 */
	List<Action> getActionChildrens(Long id);
	
	/**
	 * 删除权限
	 * @param ids
	 * @return
	 */
	boolean delAction(Object[] ids);
	
	/**
	 * 获取角色
	 * @return
	 */
	List<Role> getRoleList();
}
