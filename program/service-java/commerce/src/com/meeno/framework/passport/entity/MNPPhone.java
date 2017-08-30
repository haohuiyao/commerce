package com.meeno.framework.passport.entity;

import com.meeno.framework.bean.BaseEntity;

public class MNPPhone extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String phone;//手机号
	private MNAccount account;
	
	public MNPPhone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public MNAccount getAccount() {
		return account;
	}

	public void setAccount(MNAccount account) {
		this.account = account;
	}

}
