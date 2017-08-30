package com.meeno.framework.admin.entity;

import com.meeno.framework.bean.BaseEntity;

/**
 * @description 管理员分组
 * @time 2017年7月21日 下午5:32:32
 * @author Saturn
 * @version 1.0
 */
public class AdminGroup extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String groupName;
	private String privileges;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}
}
