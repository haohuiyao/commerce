package com.meeno.framework.passport.entity;

import java.util.Date;

import com.meeno.framework.bean.BaseEntity;

public class PhoneVCode extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String phone;//手机号
	private String vCode;
	private String vType;
	private Date createTime;
	
	
	public PhoneVCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}

	public String getvType() {
		return vType;
	}

	public void setvType(String vType) {
		this.vType = vType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
