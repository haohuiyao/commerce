package com.meeno.framework.permissions.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.permissions.entity.Action;
import com.meeno.framework.permissions.entity.Role;

@Service
public interface PermissionService extends BaseDao{
	
	/* 
	 * 获得所有权限
	 * 		type 为空：查询所有
	 * 
	 */
	List<Action> getActionList(String type);
	
	/* 
	 * 获得二级权限
	 * 
	 */
	List<Action> getActionChildrens(Long id);
	
	boolean delAction(Object[] ids);
	
	List<Role> getRoleList();
}
