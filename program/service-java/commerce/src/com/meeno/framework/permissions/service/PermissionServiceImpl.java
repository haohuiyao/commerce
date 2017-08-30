package com.meeno.framework.permissions.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.permissions.dao.PermissionDao;
import com.meeno.framework.permissions.dao.PermissionDaoImpl;
import com.meeno.framework.permissions.entity.Action;
import com.meeno.framework.permissions.entity.Role;
import com.meeno.framework.util.CommonUtil;

@Service
public class PermissionServiceImpl extends BaseDaoImpl implements PermissionService {
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Action> getActionList(String type) {
		return this.permissionDao.getActionList(type);
	}

	@Override
	public List<Action> getActionChildrens(Long id) {
		return this.permissionDao.getActionChildrens(id);
	}

	@Override
	public boolean delAction(Object[] ids) {
		return this.permissionDao.delAction(ids);
	}

	@Override
	public List<Role> getRoleList() {
		return this.permissionDao.getRoleList();
	}
}
