package com.meeno.framework.permissions.subview;

public class RoleItem {
	private Long id;
	private String roleNo;
	private String roleName;

	public RoleItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the roleNo
	 */
	public String getRoleNo() {
		return roleNo;
	}

	/**
	 * @param roleNo
	 *            the roleNo to set
	 */
	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
