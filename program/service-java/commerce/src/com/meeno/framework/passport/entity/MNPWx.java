package com.meeno.framework.passport.entity;

import com.meeno.framework.bean.BaseEntity;

public class MNPWx extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String openId;//用户统一标识
	private MNAccount account;
	
	public MNPWx() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOpenId() {
		return openId;
	}



	public void setOpenId(String openId) {
		this.openId = openId;
	}



	public MNAccount getAccount() {
		return account;
	}

	public void setAccount(MNAccount account) {
		this.account = account;
	}


}
