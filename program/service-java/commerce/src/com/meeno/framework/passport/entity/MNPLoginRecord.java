package com.meeno.framework.passport.entity;


import java.util.Date;

import com.meeno.framework.bean.BaseEntity;

public class MNPLoginRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String entryType;
	private String token;
	private Date lastLoginTime;
	private MNAccount account;
	
	
	public MNPLoginRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public MNAccount getAccount() {
		return account;
	}

	public void setAccount(MNAccount account) {
		this.account = account;
	}
	
	
}
