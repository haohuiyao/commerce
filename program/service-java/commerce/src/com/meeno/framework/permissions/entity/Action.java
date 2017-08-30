package com.meeno.framework.permissions.entity;

import com.meeno.framework.bean.BaseEntity;

public class Action extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String title;// 权限标题
	private String href;// 接口
	private Long parentId;
	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Action() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
}
