package com.meeno.framework.admin.view;

import java.io.Serializable;
import com.meeno.framework.admin.entity.AdminEntry;

/**
 * @description TODO
 * @time 2017年7月24日 下午5:28:26
 * @author Saturn
 * @version 1.0
 */
public class AdminEntryChildItemList implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String icon;
	private String route;
	private String url;
	private String templateUrl;
	private String controller;
	private String jsArr;
	private String params;
	private String isNav;

	public AdminEntryChildItemList(AdminEntry adminEntry) {
		this.id = adminEntry.getId();
		this.title = adminEntry.getTitle();
		this.icon = adminEntry.getIcon();
		this.route = adminEntry.getRoute();
		this.url = adminEntry.getUrl();
		this.templateUrl = adminEntry.getTemplateUrl();
		this.controller = adminEntry.getController();
		this.jsArr = adminEntry.getJsArr();
		this.params = adminEntry.getParams();
		this.isNav = adminEntry.getIsNav();
	}
	
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

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTemplateUrl() {
		return templateUrl;
	}

	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getJsArr() {
		return jsArr;
	}

	public void setJsArr(String jsArr) {
		this.jsArr = jsArr;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getIsNav() {
		return isNav;
	}

	public void setIsNav(String isNav) {
		this.isNav = isNav;
	}
}
