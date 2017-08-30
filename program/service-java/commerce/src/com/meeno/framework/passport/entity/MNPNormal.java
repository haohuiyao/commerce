package com.meeno.framework.passport.entity;

import com.meeno.framework.bean.BaseEntity;

public class MNPNormal extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String username;//账号
	private String pwd;//密码
	private MNAccount account;
	
	public MNPNormal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public MNAccount getAccount() {
		return account;
	}

	public void setAccount(MNAccount account) {
		this.account = account;
	}

}
