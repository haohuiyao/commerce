package com.meeno.framework.admin.view;

import java.io.Serializable;
import java.util.List;
/**
 * @description 管理后台获取管理页列表信息
 * @time 2017年7月21日 下午10:59:18
 * @author Saturn
 */
public class AdminEntryItemList implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String icon;
	private List<AdminEntryChildItemList> childs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<AdminEntryChildItemList> getChilds() {
		return childs;
	}

	public void setChilds(List<AdminEntryChildItemList> childs) {
		this.childs = childs;
	}
}
