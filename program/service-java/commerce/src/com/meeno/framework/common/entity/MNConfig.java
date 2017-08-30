package com.meeno.framework.common.entity;

import java.util.Date;

import com.meeno.framework.bean.BaseEntity;

public class MNConfig extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String name;//参数名
	private String value;//参数值
	private Date modifyTime;//更新时间
	
	public MNConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}
