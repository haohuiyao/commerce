package com.meeno.framework.permissions.entity;

import java.util.Set;

import com.meeno.framework.bean.BaseEntity;

public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String roleNo;// 角色no
	private String roleName;// 角色名
	private Set<RoleAction> actionSet;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<RoleAction> getActionSet() {
		return actionSet;
	}


	public void setActionSet(Set<RoleAction> actionSet) {
		this.actionSet = actionSet;
	}


	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


}
