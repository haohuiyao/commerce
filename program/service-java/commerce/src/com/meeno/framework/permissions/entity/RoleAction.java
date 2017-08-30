package com.meeno.framework.permissions.entity;

import com.meeno.framework.bean.BaseEntity;

public class RoleAction extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Role role;
	private Action action;

	public RoleAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
}
